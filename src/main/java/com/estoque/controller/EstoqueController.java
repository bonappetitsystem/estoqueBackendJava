package com.estoque.controller;

import java.util.List;

import com.estoque.repository.filter.EstoqueFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoque.entidades.Estoque;
import com.estoque.service.EstoqueService;

@RestController
@RequestMapping("/estoques")
@CrossOrigin(origins = "*")
public class EstoqueController {
	@Autowired
	private EstoqueService estoqueService;
	
	@PostMapping
	public ResponseEntity<Estoque> cadastrar(@RequestBody Estoque estoque) {
		Estoque estoqueSalvo = estoqueService.cadastrar(estoque);
		return ResponseEntity.status(HttpStatus.CREATED).body(estoqueSalvo);
	}
	
	@GetMapping
	public List<Estoque> pesquisar(EstoqueFilter estoqueFilter) {
		return estoqueService.pesquisar(estoqueFilter);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estoque> buscarPorId(@PathVariable Long id){
		Estoque estoque = estoqueService.buscarPorId(id);
		return ResponseEntity.ok(estoque);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Estoque> deletar(@PathVariable Long id){
		estoqueService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estoque> alterar(@PathVariable Long id, @RequestBody Estoque estoque){
		return ResponseEntity.ok(estoqueService.alterar(id, estoque));
	}
}
