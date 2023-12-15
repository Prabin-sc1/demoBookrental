package com.bookrental.bookrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_book")
public class Book {

    @Id
    @SequenceGenerator(name = "book_seq_gen", sequenceName = "book_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_gen")
    private Integer id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String name;

    private Integer noOfPages;

    @Column(columnDefinition = "VARCHAR(30)")
    private Integer isbn;
    private Double rating;
    private Integer stockCount;
    private LocalDate publishedDate;

    @Column(columnDefinition = "VARCHAR(200)")
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_book_category"))
    private Category categoryId;
}
