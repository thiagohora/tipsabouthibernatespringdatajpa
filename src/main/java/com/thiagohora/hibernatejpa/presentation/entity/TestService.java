package com.thiagohora.hibernatejpa.presentation.entity;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TestService {

    private final AuthorRepo authorRepo;

    @Transactional
    public void doSomething() {
        final List<Author> authors = authorRepo.findAll()
                                        .stream()
                                        .map(author -> {
                                            author.setSurname("Test Family");
                                            return author;
                                        })
                                        .collect(toList());

        authorRepo.save(authors);

    }
}
