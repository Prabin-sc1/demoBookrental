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
@Table(name = "tbl_member")
public class Member extends AuditActiveAbstract {
    @Id
    @SequenceGenerator(name = "member_seq_gen", sequenceName = "member_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
    private Integer id;
    @Column(unique = true)
    private String email;
    private String name;
    private String mobileNumber;
    private String address;
}
