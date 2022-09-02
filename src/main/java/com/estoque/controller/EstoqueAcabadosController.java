package com.estoque.controller;

import com.estoque.entidades.EstoqueAcabados;
import com.estoque.service.EstoqueAcabadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoquesAcabados")
@CrossOrigin(origins = "*")
public class EstoqueAcabadosController {

    @Autowired
    private EstoqueAcabadosService estoqueAcabadosService;

    @GetMapping
    public List<EstoqueAcabados> buscarTodos() {
        return estoqueAcabadosService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueAcabados> buscarPorId(@PathVariable Long id) {
        EstoqueAcabados estoqueAcabados = estoqueAcabadosService.buscarPorId(id);
        return ResponseEntity.ok(estoqueAcabados);
    }

    @PostMapping
    public ResponseEntity<EstoqueAcabados> cadastrar(@RequestBody EstoqueAcabados estoqueAcabados) {
        EstoqueAcabados estoqueAcabadosSalvo = estoqueAcabadosService.cadastrar(estoqueAcabados);
        return ResponseEntity.ok(estoqueAcabadosSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueAcabados> alterar(@PathVariable Long id, @RequestBody EstoqueAcabados estoqueAcabados) {
        return ResponseEntity.ok(estoqueAcabadosService.alterar(id, estoqueAcabados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EstoqueAcabados> deletar(@PathVariable Long id) {
        estoqueAcabadosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
