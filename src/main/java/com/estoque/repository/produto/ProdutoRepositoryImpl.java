package com.estoque.repository.produto;

import com.estoque.entidades.Produto;
import com.estoque.repository.filter.ProdutoFilter;
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

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Produto> filtrar(ProdutoFilter produtoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
        Root<Produto> root = criteria.from(Produto.class);

        Predicate[] predicate = createRestrictions(produtoFilter, builder, root);
        criteria.where(predicate);
        TypedQuery<Produto> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] createRestrictions(ProdutoFilter produtoFilter, CriteriaBuilder builder, Root<Produto> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(!ObjectUtils.isEmpty(produtoFilter.getDescricao())){
            predicates.add(builder.like(
                    builder.lower(root.get("descricao")), "%" + produtoFilter.getDescricao().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[0]);
    }
}
