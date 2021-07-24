package demo.design.level.low.parkinglot.services.authentication.repositories;

import demo.design.level.low.parkinglot.services.authentication.enums.RoleName;
import demo.design.level.low.parkinglot.services.authentication.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
