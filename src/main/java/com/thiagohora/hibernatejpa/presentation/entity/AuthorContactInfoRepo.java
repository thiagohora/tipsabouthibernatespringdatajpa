package com.thiagohora.hibernatejpa.presentation.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorContactInfoRepo extends JpaRepository<AuthorContactInfo, Long> {

}
