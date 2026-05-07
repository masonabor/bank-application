package com.edu.bankaplication.account.persistence.entity;

import com.edu.bankaplication.account.shared.enums.PostingType;
import com.edu.bankaplication.transaction.persistence.entity.Transaction;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(
        name = "postings",
        schema = "bank"
)
@SoftDelete(columnName = "deleted_at", strategy = SoftDeleteType.TIMESTAMP) // create indexes for deleted_at
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private PostingType type;

    private BigDecimal amount;

    private String description;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "transaction_id",
            nullable = false,
            updatable = false
    )
    private Transaction transaction;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
