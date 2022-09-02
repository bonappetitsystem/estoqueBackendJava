package com.estoque.controller;

import com.estoque.entidades.Receitas;
import com.estoque.repository.filter.ReceitaFilter;
import com.estoque.service.ReceitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receitas")
@CrossOrigin(origins = "*")
public class ReceitasController {

    @Autowired
    private ReceitasService receitasService;

    @GetMapping
    public List<Receitas> pesquisar(ReceitaFilter receitaFilter) {
        return receitasService.pesquisar(receitaFilter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receitas> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(receitasService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Receitas> cadastrar(@RequestBody Receitas receitas){
        return ResponseEntity.status(201).body(receitasService.cadastrar(receitas));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receitas> atualizar(@PathVariable Long id,@RequestBody Receitas receitas){
        return ResponseEntity.ok(receitasService.atualizar(id, receitas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Receitas> deletar(@PathVariable Long id){
        receitasService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
