package com.bookrental.bookrental.model;

import com.bookrental.bookrental.generic.AuditActiveAbstract;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_author")
public class Author extends AuditActiveAbstract {
    @Id
    @SequenceGenerator(name = "author_seq_gen", sequenceName = "author_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq_gen")
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String mobileNumber;
}
