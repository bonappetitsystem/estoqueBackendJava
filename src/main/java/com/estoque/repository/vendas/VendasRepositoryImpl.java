package com.estoque.repository.vendas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.ObjectUtils;

import com.estoque.entidades.Vendas;
import com.estoque.repository.filter.VendasFilter;

public class VendasRepositoryImpl implements VendasRepositoryQuery{
	   @PersistenceContext
	    private EntityManager manager;


	    @Override
	    public List<Vendas> filtrar(VendasFilter vendasFilter) {
	        CriteriaBuilder builder = manager.getCriteriaBuilder();
	        CriteriaQuery<Vendas> criteria = builder.createQuery(Vendas.class);
	        Root<Vendas> root = criteria.from(Vendas.class);

	        Predicate[] predicate = createRestrictions(vendasFilter, builder, root);
	        criteria.where(predicate);

	        TypedQuery<Vendas> query = manager.createQuery(criteria);
	        return query.getResultList();
	    }

	    private Predicate[] createRestrictions(VendasFilter vendasFilter, CriteriaBuilder builder, Root<Vendas> root) {

	        List<Predicate> predicates = new ArrayList<>();

	        if(!ObjectUtils.isEmpty(vendasFilter.getCliente())){
	            predicates.add(builder.like(
	                    builder.lower(root.get("cliente")), "%" + vendasFilter.getCliente().toLowerCase() + "%"));
	        }
	        if(!ObjectUtils.isEmpty(vendasFilter.getVendedor())){
	            predicates.add(builder.like(
	                    builder.lower(root.get("vendedor")), "%" + vendasFilter.getVendedor().toLowerCase() + "%"));
	        }
	        if(!ObjectUtils.isEmpty(vendasFilter.getCodigoVenda())){
	            predicates.add(builder.like(
	                    builder.lower(root.get("codigoVenda")), "%" + vendasFilter.getCodigoVenda().toLowerCase() + "%"));
	        }
	        return predicates.toArray(new Predicate[0]);
	    }
}
