package com.edu.bankaplication.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "bank_accounts",
        schema = "bank"
)
public class BankAccount {

    @Id
    @Column(name = "bank_account_id")
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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "due_to", nullable = false, updatable = false)
    private LocalDateTime dueTo;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
