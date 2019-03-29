package com.thiagohora.hibernatejpa.presentation.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface JpaSpecificationExecutor<T> {
    List<T> findAll(Specifications<T> specification, String entityGraphName, EntityGraph.EntityGraphType graphType);
    Page<T> findAll(Specifications<T> specification, Pageable pageable, String entityGraphName, EntityGraph.EntityGraphType graphType);
}
