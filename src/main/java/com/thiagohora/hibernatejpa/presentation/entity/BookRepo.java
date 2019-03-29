package com.thiagohora.hibernatejpa.presentation.entity;

import com.thiagohora.hibernatejpa.presentation.infrastructure.persistence.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> , JpaSpecificationExecutor<Book> {


}