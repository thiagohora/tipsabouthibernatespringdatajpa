package com.thiagohora.hibernatejpa.presentation.entity;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TestService {

    private final AuthorRepo authorRepo;
    private final AuthorContactInfoRepo contactInfoRepo ;

    @Transactional
    public void doSomething() {

        final List<Author> authors = authorRepo.findAll();
        //Business logic...

        System.out.println(authors);
    }

    @Transactional
    public void init() {
        authorRepo.findAll().forEach(author -> {
            contactInfoRepo.save(new AuthorContactInfo(author, "test@test.com", "+34651877446"));
        });
    }
}
