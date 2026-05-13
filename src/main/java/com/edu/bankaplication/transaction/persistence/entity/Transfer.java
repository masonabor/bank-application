package com.edu.bankaplication.transaction.persistence.entity;

import com.edu.bankaplication.account.persistence.entity.Posting;
import com.edu.bankaplication.account.shared.enums.Currency;
import com.edu.bankaplication.transaction.shared.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "transfers",
        schema = "bank"
)
@SoftDelete(columnName = "deleted_at", strategy = SoftDeleteType.TIMESTAMP) // create indexes for deleted_at
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "from_number", nullable = false, updatable = false)
    private String fromNumber;

    @Column(name = "to_number", nullable = false, updatable = false)
    private String toNumber;

    @Column(name = "currency", nullable = false, updatable = false)
    private Currency currency;

    @Column(name = "amount", nullable = false, updatable = false)
    private BigDecimal amount;

    @Column(name = "transaction_status", nullable = false)
    private TransactionStatus status;

    @Column(name = "transaction_comment", updatable = false)
    private String transferComment;

    @OneToMany(mappedBy = "transaction",  fetch = FetchType.LAZY)
    private Set<Posting> postings;

    @CreationTimestamp
    private Instant createdAt;
}
