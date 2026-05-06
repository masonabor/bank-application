package com.edu.bankaplication.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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

    @Column(name = "gender", nullable = false)
    private String gender;

    @OneToOne(
            cascade = CascadeType.ALL, //
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "address_info_id")
    private AddressInfo addressInfo;

    @OneToMany(
            mappedBy = "id",
            cascade = CascadeType.ALL, //
            fetch = FetchType.LAZY
    )
    private Set<BankAccount> bankAccounts;

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
