package com.estoque.repository.estoque;

import com.estoque.entidades.Estoque;
import com.estoque.repository.filter.EstoqueFilter;
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

public class EstoqueRepositoryImpl implements EstoqueRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Estoque> filtrar(EstoqueFilter estoqueFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Estoque> criteria = builder.createQuery(Estoque.class);
        Root<Estoque> root = criteria.from(Estoque.class);

        Predicate[] predicate = createRestrictions(estoqueFilter, builder, root);
        criteria.where(predicate);

        TypedQuery<Estoque> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] createRestrictions(EstoqueFilter estoqueFilter, CriteriaBuilder builder, Root<Estoque> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(!ObjectUtils.isEmpty(estoqueFilter.getLote())){
            predicates.add(builder.like(
                    builder.lower(root.get("lote")), "%" + estoqueFilter.getLote().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[0]);
    }
}
