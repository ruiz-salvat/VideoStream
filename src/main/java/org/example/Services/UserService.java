package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.Entities.Role;
import org.example.Entities.User;
import org.example.Exceptions.UserAlreadyExistsException;
import org.example.Exceptions.UserNotFoundException;
import org.example.Repositories.IRoleRepository;
import org.example.Repositories.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserByEmail(String email) {
        if (!userRepository.existsByEmail(email))
            throw new UserNotFoundException();

        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByUserName(String userName) {
        if (!userRepository.existsByUserName(userName))
            throw new UserNotFoundException();

        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUserName(user.getUserName()))
            throw new UserAlreadyExistsException();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRoleName("ADMIN");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        return userRepository.save(user);
    }

    @Override
    public List<String> getAllUsers() {
        return userRepository.getAllEntries();
    }
}
