package com.marom.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Book {

    @Id
    private String isn;

    private String title;
    private String publisher;
    @ElementCollection
    private List<String> authors;
    private String publishedDate;



}
