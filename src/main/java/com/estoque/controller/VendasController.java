package com.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.estoque.entidades.Vendas;
import com.estoque.service.VendasService;

@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "*")
public class VendasController {

    @Autowired
    private VendasService vendasService;

    @GetMapping
    public List<Vendas> buscarTodos() {
        return vendasService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendas> buscarPorId(@PathVariable Long id) {
        Vendas vendas = vendasService.buscarPorId(id);
        return ResponseEntity.ok(vendas);
    }

    @PostMapping
    public ResponseEntity<Vendas> cadastrar(@RequestBody Vendas vendas) {
        Vendas vendasSalvo = vendasService.cadastrar(vendas);
        return ResponseEntity.ok(vendasSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendas> alterar(@PathVariable Long id, @RequestBody Vendas vendas) {
        return ResponseEntity.ok(vendasService.alterar(id, vendas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Vendas> deletar(@PathVariable Long id) {
        vendasService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
