package demo.design.level.low.parkinglot.services.authentication.services;

import demo.design.level.low.parkinglot.services.authentication.enums.RoleName;
import demo.design.level.low.parkinglot.services.authentication.entities.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(RoleName name);

}
