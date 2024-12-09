package com.example.collabsphere.CollabSphere.model.postgresql;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_postgres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPostgres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;
}
