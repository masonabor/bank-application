package com.edu.bankaplication.user.persistance.entity;

import com.edu.bankaplication.account.persistence.entity.Account;
import com.edu.bankaplication.user.shared.enums.Gender;
import com.edu.bankaplication.user.shared.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "users",
        schema = "bank"
)
@SoftDelete(columnName = "deleted_at", strategy = SoftDeleteType.TIMESTAMP) // create indexes for deleted_at
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @OneToOne(
            cascade = CascadeType.ALL, // check what is cascade types
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "address_info_id",
            nullable = false
    )
    private AddressInfo addressInfo;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL, // check what is cascade types
            fetch = FetchType.LAZY
    )
    private Set<Account> accounts;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "alternative_phone_number")
    private String alternativePhoneNumber;

    @Column(name = "status", nullable = false)
    @Builder.Default
    private Status status = Status.INACTIVE;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
