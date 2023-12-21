package com.bookrental.bookrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_author")
public class Author {
    @Id
    @SequenceGenerator(name = "author_seq_gen", sequenceName = "author_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq_gen")
    private Integer id;
    private String name;
    private String email;
    private String mobileNumber;
//
//    @ManyToMany(mappedBy = "authors")
//    private List<Book> book = new ArrayList<>();
}
