package com.edu.bankapplication.web.auth.persistance.entity;

import com.edu.bankapplication.user.persistance.entity.IdentityUser;
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
        name = "refresh_tokens",
        schema = "bank",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "user_id",
                        "refresh_token_hash"}
                )
        }
)
public class RefreshToken implements Token<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            updatable = false
    )
    private IdentityUser user;

    @Column(
            name = "refresh_token_hash",
            nullable = false,
            updatable = false
    )
    private String refreshTokenHash;

    @Column(name = "expires_at", nullable = false, updatable = false)
    private Instant expiresAt;

    @Override
    public String getTokenHash() {
        return refreshTokenHash;
    }

    public boolean isExpired() {
        Objects.requireNonNull(expiresAt, "expires at is null");
        return Instant.now().isBefore(expiresAt);
    }
}
