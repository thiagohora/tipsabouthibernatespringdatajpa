package com.thiagohora.hibernatejpa.presentation.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "author_contact_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString(of = {"id", "email", "phone"})
@EqualsAndHashCode(of = "id")
public class AuthorContactInfo {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private Author author;

    private String email;
    private String phone;

    public AuthorContactInfo(Author author, String email, String phone) {
        this.author = author;
        this.email = email;
        this.phone = phone;
    }
}
