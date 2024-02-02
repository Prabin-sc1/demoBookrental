package com.bookrental.bookrental.model;

import com.bookrental.bookrental.generic.AuditActiveAbstract;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_book", uniqueConstraints = {
        @UniqueConstraint(name = "uc_book", columnNames = {
                "name","isbn"
        })
})
public class Book extends AuditActiveAbstract {

    @Id
    @SequenceGenerator(name = "book_seq_gen", sequenceName = "book_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_gen")
    private Integer id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String name;
    private Integer noOfPages;
    private Long isbn;
    private Double rating;
    private Integer stockCount;
    private LocalDate publishedDate;

//    @Column(columnDefinition = "VARCHAR(200)")
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_book_category"))
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_book_author", joinColumns = {
            @JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_book_author_tbl_book"))
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_book_author_tbl_author"))
            }
    )
    private List<Author> authors;
}
