package com.example.collabsphere.CollabSphere.repository.mysql;

import com.example.collabsphere.CollabSphere.model.mysql.UserMySQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMySQLRepository extends JpaRepository<UserMySQL, Long> {
}
