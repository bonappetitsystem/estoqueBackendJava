package com.estoque.repository.estoqueAcabado;

import com.estoque.entidades.EstoqueAcabado;
import com.estoque.entidades.EstoqueAcabado_;
import com.estoque.repository.filter.EstoqueAcabadoFilter;
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

public class EstoqueAcabadoRepositoryImpl implements EstoqueAcabadoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<EstoqueAcabado> filtrar(EstoqueAcabadoFilter estoqueAcabadoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<EstoqueAcabado> criteria = builder.createQuery(EstoqueAcabado.class);
        Root<EstoqueAcabado> root = criteria.from(EstoqueAcabado.class);

        Predicate[] predicates = createRestrictions(estoqueAcabadoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<EstoqueAcabado> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] createRestrictions(EstoqueAcabadoFilter estoqueAcabadoFilter, CriteriaBuilder builder, Root<EstoqueAcabado> root) {
        List<Predicate> predicates = new ArrayList<>();


        if (!ObjectUtils.isEmpty(estoqueAcabadoFilter.getNome())) {
//


//            predicates.add(builder.like(builder.lower(root.get(EstoqueAcabado_.RECEITAS)), "%" + estoqueAcabadoFilter.getNome().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }


}
