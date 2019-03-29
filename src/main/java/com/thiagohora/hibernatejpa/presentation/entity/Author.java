package com.thiagohora.hibernatejpa.presentation.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@NamedEntityGraph(
        name = "Author.graph", attributeNodes ={
            @NamedAttributeNode(value = "address", subgraph = "address")
        },
        subgraphs = {
            @NamedSubgraph(name = "address", attributeNodes ={
                @NamedAttributeNode(value = "city", subgraph = "city")
            }),
            @NamedSubgraph(name = "city", attributeNodes = {
                @NamedAttributeNode(value = "country")
            })

})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString(of = {"id", "name", "surname"})
@EqualsAndHashCode(of = "id")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @BatchSize(size = 2)
    private Set<Book> books = new HashSet<>();

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}