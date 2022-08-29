package com.estoque.controller;

import java.util.List;

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

import com.estoque.entidades.Produto;
import com.estoque.service.ProdutoService;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public List<Produto> buscarTodos(){
		return produtoService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.buscarPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrar(produto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> alterar(@PathVariable Long id, @RequestBody Produto produto){
		return ResponseEntity.ok(produtoService.alterar(id, produto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deletar(@PathVariable Long id){
		produtoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
