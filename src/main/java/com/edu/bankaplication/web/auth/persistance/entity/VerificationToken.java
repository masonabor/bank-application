package com.edu.bankaplication.web.auth.persistance.entity;

import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.common.TemporalUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "verification_tokens",
        schema = "bank",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "user_id",
                        "verification_token_hash"
                })
        }
)
public class VerificationToken implements Token<String>{

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
            name = "verification_token_hash",
            nullable = false,
            updatable = false
    )
    private String verificationTokenHash;

    @Column(name = "expires_at", nullable = false, updatable = false)
    private Instant expiresAt;

    @Override
    public String getToken() {
        return verificationTokenHash;
    }
}
