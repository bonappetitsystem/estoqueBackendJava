package com.estoque.controller;

import com.estoque.entidades.EstoqueAcabado;
import com.estoque.repository.filter.EstoqueAcabadoFilter;
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
    public List<EstoqueAcabado> pesquisar(EstoqueAcabadoFilter estoqueAcabadoFilter) {
        return estoqueAcabadosService.pesquisar(estoqueAcabadoFilter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueAcabado> buscarPorId(@PathVariable Long id) {
        EstoqueAcabado estoqueAcabado = estoqueAcabadosService.buscarPorId(id);
        return ResponseEntity.ok(estoqueAcabado);
    }

    @PostMapping
    public ResponseEntity<EstoqueAcabado> cadastrar(@RequestBody EstoqueAcabado estoqueAcabado) {
        EstoqueAcabado estoqueAcabadoSalvo = estoqueAcabadosService.cadastrar(estoqueAcabado);
        return ResponseEntity.ok(estoqueAcabadoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueAcabado> alterar(@PathVariable Long id, @RequestBody EstoqueAcabado estoqueAcabado) {
        return ResponseEntity.ok(estoqueAcabadosService.alterar(id, estoqueAcabado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EstoqueAcabado> deletar(@PathVariable Long id) {
        estoqueAcabadosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
