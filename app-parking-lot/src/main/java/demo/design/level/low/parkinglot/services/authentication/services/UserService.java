package demo.design.level.low.parkinglot.services.authentication.services;

import demo.design.level.low.parkinglot.services.authentication.entities.User;

import java.util.Optional;

public interface UserService {

    boolean existsByUsername(String username);

    User save(User user);

    boolean existsByEmail(String username);

    Optional<User> findByUsername(String username);

}
