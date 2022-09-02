package com.estoque.repository.receita;

import com.estoque.entidades.Receitas;
import com.estoque.repository.filter.ReceitaFilter;
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


public class ReceitasRepositoryImpl implements ReceitasRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Receitas> filtrar(ReceitaFilter receitaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Receitas> criteria = builder.createQuery(Receitas.class);
        Root<Receitas> root = criteria.from(Receitas.class);

        Predicate[] predicate = createRestrictions(receitaFilter, builder, root);
        criteria.where(predicate);
        TypedQuery<Receitas> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] createRestrictions(ReceitaFilter receitaFilter, CriteriaBuilder builder, Root<Receitas> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(!ObjectUtils.isEmpty(receitaFilter.getTitulo())){
            predicates.add(builder.like(
                    builder.lower(root.get("titulo")), "%" + receitaFilter.getTitulo().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[0]);
    }
}
