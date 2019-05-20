package com.example.parkinglot.services.impl;

import com.example.parkinglot.constants.enums.roles.RoleName;
import com.example.parkinglot.entities.Role;
import com.example.parkinglot.repos.RoleRepo;
import com.example.parkinglot.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepo.findByName(name);
    }

}
