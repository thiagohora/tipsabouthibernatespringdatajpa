package com.thiagohora.hibernatejpa.presentation.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
import javax.persistence.Table;

@Entity
@Table(name = "book")
@NamedEntityGraph(
        name = "Book.graph",
        attributeNodes = {
                @NamedAttributeNode(value = "author", subgraph = "author"),
                @NamedAttributeNode("publisher"),
        },
        subgraphs = {
                @NamedSubgraph(name = "author", attributeNodes ={
                        @NamedAttributeNode(value = "address", subgraph = "address")
                }),
                @NamedSubgraph(name = "address", attributeNodes ={
                        @NamedAttributeNode(value = "city")
                })
        })
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "name" })
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book(String name, Publisher publisher, Author author) {
        this.publisher = publisher;
        this.name = name;
        this.author = author;
    }
}
