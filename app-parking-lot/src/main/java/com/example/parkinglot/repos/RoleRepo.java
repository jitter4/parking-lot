package com.example.parkinglot.repos;

import com.example.parkinglot.constants.enums.roles.RoleName;
import com.example.parkinglot.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
