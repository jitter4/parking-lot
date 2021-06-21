package demo.design.level.low.parkinglot.services.authentication.services.implementations;

import demo.design.level.low.parkinglot.services.authentication.enums.RoleName;
import demo.design.level.low.parkinglot.services.authentication.entities.Role;
import demo.design.level.low.parkinglot.services.authentication.repositories.RoleRepository;
import demo.design.level.low.parkinglot.services.authentication.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return this.roleRepository.findByName(name);
    }

}
