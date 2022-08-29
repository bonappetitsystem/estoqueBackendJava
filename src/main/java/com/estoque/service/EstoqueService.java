package com.estoque.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.estoque.entidades.Estoque;
import com.estoque.repository.EstoqueRepository;

@Service
public class EstoqueService {

	@Autowired
	EstoqueRepository estoqueRepository;
	
	public Estoque cadastrar(Estoque estoque) {
		return estoqueRepository.save(estoque); 
	}
	
	public List<Estoque> buscarTodos() {
		return 	estoqueRepository.findAll();
	}
	
	public Estoque buscarPorId(Long id) {
		return estoqueRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	public void deletar(Long id) {
//		buscarPorId(id);
		estoqueRepository.deleteById(id);
	}
	
	public Estoque alterar(Long id, Estoque estoque) {
		Estoque estoqueSalvo = buscarPorId(id);
		BeanUtils.copyProperties(estoque, estoqueSalvo, "id_estoque");
		return estoqueRepository.save(estoqueSalvo);
	}
}
