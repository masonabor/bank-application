package com.edu.bankaplication.account.persistence.entity;

import com.edu.bankaplication.account.core.exception.InsufficientBalanceException;
import com.edu.bankaplication.account.core.exception.InvalidBalanceAmountException;
import com.edu.bankaplication.account.shared.enums.Currency;
import com.edu.bankaplication.account.shared.enums.Status;
import com.edu.bankaplication.user.persistance.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
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
//there is a problem with lazy initializing in many-to-one and soft delete annotation, so you need to replace @SoftDelete with @SQLDelete and @Where
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

    @Column(name = "currency", nullable = false, updatable = false)
    private Currency currency;

    @Column(name = "balance", nullable = false)
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(
            mappedBy = "account",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Posting> postings;

    // TODO change technical status to business status
    @Column(name = "status", nullable = false)
    private Status status;

    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "due_to", nullable = false, updatable = false)
    private Instant dueTo;

    @UpdateTimestamp
    private Instant updatedAt;

    public void withdraw(BigDecimal amount) {
        requirePositive(amount);

        if (balance.compareTo(amount) < 0)
            throw new InsufficientBalanceException("Balance cannot be negative");

        balance = balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        requirePositive(amount);
        balance = balance.add(amount);
    }

    private void requirePositive(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new InvalidBalanceAmountException("amount must be positive");
        }
    }


}
