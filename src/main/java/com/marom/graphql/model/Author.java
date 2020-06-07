package com.marom.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table
@Getter
@Setter
@Entity
public class Author {

    @Id
    @Column(name="author_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="author_first_name", nullable = false)
    private String firstName;

    @Column(name="author_last_name", nullable = false)
    private String lastName;

    public Author() {
    }

    public Author(Long id) {
        this.id = id;
    }
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
