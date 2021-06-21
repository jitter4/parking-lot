package demo.design.level.low.parkinglot.services.authentication.services.implementations;

import demo.design.level.low.parkinglot.services.authentication.entities.User;
import demo.design.level.low.parkinglot.services.authentication.repositories.UserRepository;
import demo.design.level.low.parkinglot.services.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean existsByUsername(final String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public User save(final User user) {
        return this.userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }
}
