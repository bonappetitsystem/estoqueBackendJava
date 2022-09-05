package com.estoque.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.estoque.entidades.EstoqueAcabados;
import com.estoque.entidades.Vendas;
import com.estoque.repository.VendasRepository;
import com.estoque.repository.filter.VendasFilter;

@Service
public class VendasService {

	@Autowired
	VendasRepository vendasRepository;
	
	@Autowired
	EstoqueAcabadosService estoqueAcabadosService;
	
    public List<Vendas> buscarTodos() {
        return vendasRepository.findAll();
    }
	
	public Vendas cadastrar(Vendas vendas) {
		atualizarEstoque(vendas);
		return vendasRepository.save(vendas); 
	}
	
	public void atualizarEstoque(Vendas vendas) {
		EstoqueAcabados estoqueAcabado = estoqueAcabadosService.buscarPorId(vendas.getEstoqueAcabado().getId());
		if(estoqueAcabado.getQuantidade() >= vendas.getQuantidadeUnitaria()) {
			estoqueAcabado.setQuantidade(estoqueAcabado.getQuantidade() - vendas.getQuantidadeUnitaria());
			estoqueAcabadosService.alterar(estoqueAcabado.getId(), estoqueAcabado);
			vendasRepository.save(vendas);
		}
		
	}
	
	public List<Vendas> pesquisar(VendasFilter vendasFilter) {
		return 	vendasRepository.filtrar(vendasFilter);
	}
	
	public Vendas buscarPorId(Long id) {
		return vendasRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	public void deletar(Long id) {
		buscarPorId(id);
		vendasRepository.deleteById(id);
	}
	
	public Vendas alterar(Long id, Vendas vendas) {
		Vendas vendasSalvo = buscarPorId(id);
		BeanUtils.copyProperties(vendas, vendasSalvo, "id_vendas");
		return vendasRepository.save(vendasSalvo);
	}
}
