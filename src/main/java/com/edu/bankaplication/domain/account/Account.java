package com.edu.bankaplication.domain.account;

import com.edu.bankaplication.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "accounts",
        schema = "bank"
)
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
            mappedBy = "id",
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
