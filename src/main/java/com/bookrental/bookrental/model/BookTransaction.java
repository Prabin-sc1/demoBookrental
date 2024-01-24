package com.bookrental.bookrental.model;

import com.bookrental.bookrental.enums.RentType;
import com.bookrental.bookrental.generic.AuditActiveAbstract;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_book_transaction")
public class BookTransaction extends AuditActiveAbstract {
    @Id
    @SequenceGenerator(name = "transaction_seq_gen", sequenceName = "transaction_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq_gen")
    private Integer id;
    @Column(unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private LocalDate fromDate;
    @Column(nullable = false)
    private LocalDate toDate;
    @Column(name = "rent_status")
    @Enumerated(EnumType.STRING)
    private RentType rentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_booktransaction_member"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_booktransaction_book"))
    private Book book;
}
