package com.estoque.service;

import com.estoque.entidades.EstoqueAcabado;
import com.estoque.entidades.Venda;
import com.estoque.exceptionHandler.custom.EstoqueInsuficiente;
import com.estoque.repository.VendaRepository;
import com.estoque.repository.filter.VendaFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired EstoqueAcabadosService estoqueAcabadosService;

    public List<Venda> pesquisar(VendaFilter vendaFilter) {
        return vendaRepository.filtrar(vendaFilter);
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Venda salvar(Venda venda) {

            atualizarEstoque(venda);
        return vendaRepository.save(venda);
    }

    // Atualiza o estoque de acordo com a venda e calcula o valor total da venda
    private void atualizarEstoque(Venda venda) {
        EstoqueAcabado estoqueAcabado = estoqueAcabadosService.buscarPorId(venda.getEstoqueAcabado().getId());
        if (estoqueAcabado.getQuantidade() < venda.getQuantidade()) {
            throw new EstoqueInsuficiente();
        }
        estoqueAcabado.setQuantidade(estoqueAcabado.getQuantidade() - venda.getQuantidade());
        estoqueAcabadosService.alterar(estoqueAcabado.getId(), estoqueAcabado);
    }


    public void deletar(Long id) {
        buscarPorId(id);
        vendaRepository.deleteById(id);
    }

    public Venda atualizar(Long id, Venda venda){
        Venda vendaSalva = buscarPorId(id);
        BeanUtils.copyProperties(venda, vendaSalva, "id");
        return vendaRepository.save(vendaSalva);
    }



}
