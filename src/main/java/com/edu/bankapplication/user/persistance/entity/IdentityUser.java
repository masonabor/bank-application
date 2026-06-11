package com.edu.bankapplication.user.persistance.entity;

import com.edu.bankapplication.user.shared.enums.Role;
import com.edu.bankapplication.user.shared.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "identity_users",
        schema = "bank",
        indexes = {
                @Index(name = "idx_identity_users_email", columnList = "email")
        }
)
@SoftDelete(columnName = "deleted_at", strategy = SoftDeleteType.TIMESTAMP)
public class IdentityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "alternative_phone_number")
    private String alternativePhoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "status", nullable = false)
    @Builder.Default
    private Status status = Status.INACTIVATED;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Version
    private Long version;
}
