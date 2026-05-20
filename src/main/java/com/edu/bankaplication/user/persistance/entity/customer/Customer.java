package com.edu.bankaplication.user.persistance.entity.customer;

import com.edu.bankaplication.account.persistence.entity.Account;
import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import com.edu.bankaplication.user.shared.enums.Gender;
import com.edu.bankaplication.user.shared.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "identity_user_id", nullable = false)
    private IdentityUser identityUser;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @OneToOne(
            cascade = CascadeType.ALL, // check what is cascade types
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "address_info_id")
    private AddressInfo addressInfo;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL, // check what is cascade types
            fetch = FetchType.LAZY
    )
    private Set<Account> accounts;

    @Column(name = "status", nullable = false)
    @Builder.Default
    private Status status = Status.INACTIVE;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Version
    private Long version;
}
