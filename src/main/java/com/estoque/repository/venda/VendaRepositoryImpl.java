package com.estoque.repository.venda;

import com.estoque.entidades.Venda;
import com.estoque.repository.filter.VendaFilter;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class VendaRepositoryImpl implements VendaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Venda> filtrar(VendaFilter vendaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Venda> criteria = builder.createQuery(Venda.class);
        Root<Venda> root = criteria.from(Venda.class);

        Predicate[] predicates = criarRestricoes(vendaFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Venda> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] criarRestricoes(VendaFilter vendaFilter, CriteriaBuilder builder, Root<Venda> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(vendaFilter.getCodigoVenda())) {
            predicates.add(builder.like(
                    builder.lower(root.get("codigoVenda")), "%" + vendaFilter.getCodigoVenda().toLowerCase() + "%"));
        }

        if(!ObjectUtils.isEmpty(vendaFilter.getDataVenda())){
            predicates.add(builder.like(
                    builder.lower(root.get("dataVenda")), "%" + vendaFilter.getDataVenda().toLowerCase() + "%"));
        }

        if(!ObjectUtils.isEmpty(vendaFilter.getNomeCliente())){
            predicates.add(builder.like(
                    builder.lower(root.get("nomeCliente")), "%" + vendaFilter.getNomeCliente().toLowerCase() + "%"));
        }

        if(!ObjectUtils.isEmpty(vendaFilter.getNomeVendedor())){
            predicates.add(builder.like(
                    builder.lower(root.get("nomeVendedor")), "%" + vendaFilter.getNomeVendedor().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
