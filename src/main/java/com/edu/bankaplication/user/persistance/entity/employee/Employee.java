package com.edu.bankaplication.user.persistance.entity.employee;

import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import com.edu.bankaplication.user.shared.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "employees",
        schema = "bank"
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "identity_user_id", nullable = false)
    private IdentityUser identityUser;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    // TODO employee entity
}
