package com.edu.bankaplication.web.auth.persistance.entity;

import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "activation_tokens",
        schema = "bank",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "user_id",
                        "activation_token_hash"
                })
        }
)
public class ActivationToken implements Token<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            updatable = false
    )
    private IdentityUser user;

    @Column(
            name = "activation_token_hash",
            nullable = false,
            updatable = false
    )
    private String activationTokenHash;

    @Column(name = "expires_at", nullable = false, updatable = false)
    private Instant expiresAt;

    @Override
    public String getTokenHash() {
        return activationTokenHash;
    }

    public boolean isExpired() {
        Objects.requireNonNull(expiresAt, "expires at is null");
        return Instant.now().isBefore(expiresAt);
    }
}
