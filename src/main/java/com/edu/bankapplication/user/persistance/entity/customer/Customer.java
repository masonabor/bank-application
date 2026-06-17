package com.edu.bankapplication.user.persistance.entity.customer;

import com.edu.bankapplication.account.persistence.entity.Account;
import com.edu.bankapplication.user.persistance.entity.IdentityUser;
import com.edu.bankapplication.user.shared.enums.Gender;
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
        name = "customers",
        schema = "bank"
)
@SoftDelete(columnName = "deleted_at", strategy = SoftDeleteType.TIMESTAMP) // create indexes for deleted_at
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_seq")
    @SequenceGenerator(name = "customers_seq", sequenceName = "customers_seq", schema = "bank", allocationSize = 50)
    private Long id;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "identity_user_id", nullable = false)
    private IdentityUser identityUser;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Enumerated(EnumType.STRING)
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

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Version
    private Long version;
}
