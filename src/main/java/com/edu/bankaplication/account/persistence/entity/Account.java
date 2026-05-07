package com.edu.bankaplication.account.persistence.entity;

import com.edu.bankaplication.user.persistance.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(
        name = "accounts",
        schema = "bank"
)
@SoftDelete(columnName = "deleted_at", strategy = SoftDeleteType.TIMESTAMP) // create indexes for deleted_at
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(
            name = "card_number",
            nullable = false,
            unique = true,
            updatable = false
    )
    private String cardNumber;

    @Column(name = "balance", nullable = false)
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(
            mappedBy = "account",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Posting> postings;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "due_to", nullable = false, updatable = false)
    private LocalDateTime dueTo;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
