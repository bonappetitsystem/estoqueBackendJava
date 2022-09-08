package com.estoque.controller;

import com.estoque.entidades.Venda;
import com.estoque.repository.filter.VendaFilter;
import com.estoque.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "*")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> pesquisar(VendaFilter vendaFilter) {
        return vendaService.pesquisar(vendaFilter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Venda> salvar(@RequestBody Venda venda) {
        return ResponseEntity.status(201).body(vendaService.salvar(venda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda venda) {
        return ResponseEntity.ok(vendaService.atualizar(id, venda));
    }
}
