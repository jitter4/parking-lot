package com.example.parkinglot.services;

import com.example.parkinglot.constants.enums.roles.RoleName;
import com.example.parkinglot.entities.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(RoleName name);

}
