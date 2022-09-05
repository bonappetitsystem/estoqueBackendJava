package com.estoque.controller;

import com.estoque.entidades.ProdutosAcabados;
import com.estoque.service.ProdutoAcabadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoques-acabados")
public class ProdutoAcabadosController {

    @Autowired
    private ProdutoAcabadosService produtoAcabadosService;

    @GetMapping
    public List<ProdutosAcabados> buscarTodos() {
        return produtoAcabadosService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosAcabados> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(produtoAcabadosService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutosAcabados> cadastrar(@RequestBody ProdutosAcabados estoqueAcabados) {
        ProdutosAcabados produtosAcabadosSalvo = produtoAcabadosService.cadastrar(estoqueAcabados);
        return ResponseEntity.ok(produtosAcabadosSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutosAcabados> alterar(@PathVariable Long id, @RequestBody ProdutosAcabados produtosAcabados) {
        return ResponseEntity.ok(produtoAcabadosService.alterar(id, produtosAcabados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutosAcabados> deletar(@PathVariable Long id) {
        produtoAcabadosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
