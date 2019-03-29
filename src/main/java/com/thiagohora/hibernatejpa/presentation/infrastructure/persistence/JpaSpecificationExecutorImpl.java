package com.thiagohora.hibernatejpa.presentation.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class JpaSpecificationExecutorImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>  implements JpaSpecificationExecutor<T> {

    private final EntityManager entityManager;

    public JpaSpecificationExecutorImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    private void addEntityGraph(String entityGraphName, final EntityGraph.EntityGraphType graphType, final TypedQuery<T> query) {
        Optional.ofNullable(entityGraphName)
                .filter(name -> graphType != null)
                .ifPresent(name -> query.setHint(graphType.getKey(), this.entityManager.createEntityGraph(name)));
    }

    @Override
    public List<T> findAll(Specifications<T> spec, String entityGraphName, EntityGraph.EntityGraphType graphType) {
        final TypedQuery<T> query = this.getQuery(spec, (Sort) null);
        addEntityGraph(entityGraphName, graphType, query);
        return query.getResultList();
    }


    public Page<T> findAll(Specifications<T> spec, Pageable pageable, String entityGraphName, EntityGraph.EntityGraphType graphType) {
        final TypedQuery<T> query = this.getQuery(spec, pageable);
        addEntityGraph(entityGraphName, graphType, query);
        return pageable == null ? new PageImpl<>(query.getResultList()) : readPage(query, pageable, spec);
    }
}
