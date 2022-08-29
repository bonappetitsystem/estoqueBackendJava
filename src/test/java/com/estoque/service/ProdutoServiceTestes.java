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

import com.estoque.entidades.Produto;
import com.estoque.repository.ProdutoRepository;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTestes {
	private Long idExistent;
	private Long idInexistent;

	private Produto product;
	private Produto productInvalid;
	
	@BeforeEach
	void setup() {
		idExistent = 1l;
		idInexistent = 1000l;

		product = new Produto();
		productInvalid = new Produto();					
	}
	
	@InjectMocks
	ProdutoService productService;
	
	@Mock
	ProdutoRepository productRepository;

	@Test
	public void returnExceptionWhenFailToSave() {
		Mockito.doThrow(IllegalArgumentException.class).when(productRepository).save(productInvalid);
		Assertions.assertThrows(IllegalArgumentException.class, () -> productService.cadastrar(productInvalid));
		Mockito.verify(productRepository).save(productInvalid);
		
	}
	
	@Test
	public void returnInventoryWhenSuccess() {
		Mockito.when(productRepository.save(product)).thenReturn(product);
		Produto productTest = productService.cadastrar(product);
		Assertions.assertNotNull(productTest);
		Mockito.verify(productRepository).save(product);
	}
	
	@Test
	public void returNothingWhenIdExists() {
		Mockito.doNothing().when(productRepository).deleteById(idExistent);
		Assertions.assertDoesNotThrow(() -> {
			productService.deletar(idExistent);
		});
		Mockito.verify(productRepository,Mockito.times(1)).deleteById(idExistent);
	}
	
	@Test
	public void throwEmptyResultDataAcessWhenIdDoesNotExist() {
		Mockito.doThrow(EmptyResultDataAccessException.class).when(productRepository).findById(idInexistent);
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			productService.deletar(idInexistent);
		});
		Mockito.verify(productRepository,Mockito.times(1)).deleteById(idInexistent);
	}
	
	@Test
	public void returnInventoryWhenIdExists() {		
		Mockito.when(productRepository.findById(idExistent)).thenReturn(Optional.of(product));
		Produto productTest = productService.buscarPorId(idExistent); 
		Assertions.assertNotNull(productTest);
		Mockito.verify(productRepository).findById(idExistent);
	}
	
	@Test
	public void throwEntityNotFoundWhenIdDoesNotExist() {
		Mockito.doThrow(EmptyResultDataAccessException.class).when(productRepository).deleteById(idInexistent);
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			productService.buscarPorId(idInexistent);
		});
		Mockito.verify(productRepository,Mockito.times(1)).findById(idInexistent);
	}
}
