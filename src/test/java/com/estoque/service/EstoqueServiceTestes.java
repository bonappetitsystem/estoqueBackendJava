package com.estoque.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.estoque.entidades.Estoque;
import com.estoque.repository.EstoqueRepository;

@ExtendWith(SpringExtension.class)
public class EstoqueServiceTestes {
	private Long idExistente;
	private Long idNaoExistente;

	private Estoque inventory;
	private Estoque inventoryInvalid;
	
	@BeforeEach
	void setup() {
		idExistente = 1l;
		idNaoExistente = 1000l;

		inventory = new Estoque();
		inventoryInvalid = new Estoque();
						
			Mockito.doThrow(IllegalArgumentException.class).when(inventoryRepository).save(inventoryInvalid);
		Mockito.when(inventoryRepository.save(inventory)).thenReturn(inventory);
		

		Mockito.doNothing().when(inventoryRepository).deleteById(idExistente);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(inventoryRepository).deleteById(idNaoExistente);
		
		Mockito.when(inventoryRepository.findById(idExistente)).thenReturn(Optional.of(inventory));
		Mockito.doThrow(EmptyResultDataAccessException.class).when(inventoryRepository).findById(idNaoExistente);
	}
	
	@InjectMocks
	EstoqueService inventoryService;
	
	@Mock
	EstoqueRepository inventoryRepository;

	@Test
	public void returnExceptionWhenFail() {
		Mockito.doThrow(IllegalArgumentException.class).when(inventoryRepository).save(inventoryInvalid);
		Assertions.assertThrows(IllegalArgumentException.class, () -> inventoryService.cadastrar(inventoryInvalid));
		Mockito.verify(inventoryRepository).save(inventoryInvalid);
		
	}
	
	@Test
	public void returnInventoryWhenSuccess() {
		Mockito.when(inventoryRepository.save(inventory)).thenReturn(inventory);
		Estoque inventoryTest = inventoryService.cadastrar(inventory);
		Assertions.assertNotNull(inventoryTest);
		Mockito.verify(inventoryRepository).save(inventory);
	}
	
	@Test
	public void returNothingWhenIdExists() {
		Mockito.doNothing().when(inventoryRepository).deleteById(idExistente);
		Assertions.assertDoesNotThrow(() -> {
			inventoryService.deletar(idExistente);
		});
		Mockito.verify(inventoryRepository,Mockito.times(1)).deleteById(idExistente);
	}
	
	@Test
	public void throwEmptyResultDataAcessWhenIdDoesNotExist() {
		Mockito.doThrow(EmptyResultDataAccessException.class).when(inventoryRepository).deleteById(idNaoExistente);
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			inventoryService.deletar(idNaoExistente);
		});
		Mockito.verify(inventoryRepository,Mockito.times(1)).deleteById(idNaoExistente);
	}
	
	@Test
	public void returnInventoryWhenIdExists() {
		Mockito.when(inventoryRepository.findById(idExistente)).thenReturn(Optional.of(inventory));
		Estoque inventoryTest = inventoryService.buscarPorId(idExistente); 
		Assertions.assertNotNull(inventoryTest);
		Mockito.verify(inventoryRepository).findById(idExistente);
	}
	
	@Test
	public void throwEntityNotFoundWhenIdDoesNotExist() {
		Mockito.doThrow(EmptyResultDataAccessException.class).when(inventoryRepository).findById(idNaoExistente);
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			inventoryService.buscarPorId(idNaoExistente);
		});
		Mockito.verify(inventoryRepository,Mockito.times(1)).findById(idNaoExistente);
	}

	@Test
	public void returnInventoryWhenSuccessUpdate() {
		Estoque inventoryTest = inventoryService.alterar(idExistente, inventory);
		Assertions.assertNotNull(inventoryTest);
		Mockito.verify(inventoryRepository).save(inventory);
	}

	@Test
	public void throwEntityNotFoundWhenIdDoesNotExistUpdate() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			inventoryService.alterar(idNaoExistente, inventory);
		});
		Mockito.verify(inventoryRepository,Mockito.times(1)).findById(idNaoExistente);
	}
}
