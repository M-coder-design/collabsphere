package com.example.collabsphere.CollabSphere.repository.postgresql;

import com.example.collabsphere.CollabSphere.model.postgresql.UserPostgres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostgresRepository extends JpaRepository<UserPostgres, Long> {
}
