package com.edu.bankaplication.domain.transaction;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "transactions",
        schema = "bank"
)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "from_number", nullable = false, updatable = false)
    private String fromNumber;

    @Column(name = "to_number", nullable = false, updatable = false)
    private String toNumber;

    @Column(name = "amount", nullable = false, updatable = false)
    private BigDecimal amount;

    @Column(name = "transaction_comment", updatable = false)
    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
