package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.Entities.ApplicationUser;
import org.example.Entities.Role;
import org.example.Exceptions.MissingFieldsException;
import org.example.Exceptions.UserAlreadyExistsException;
import org.example.Exceptions.UserNotFoundException;
import org.example.Repositories.IRoleRepository;
import org.example.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
// @AllArgsConstructor
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IRoleRepository roleRepository;

//    @Autowired
//    public UserService(IUserRepository userRepository, IRoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }

    @Override
    public ApplicationUser getUserByEmail(String email) {
        if (!userRepository.existsByEmail(email))
            throw new UserNotFoundException();

        return userRepository.findByEmail(email);
    }

    @Override
    public ApplicationUser getUserByUserName(String userName) {
        if (!userRepository.existsByUserName(userName))
            throw new UserNotFoundException();

        return userRepository.findByUserName(userName);
    }

    @Override
    public ApplicationUser saveUser(ApplicationUser applicationUser) {
        if (userRepository.existsByEmail(applicationUser.getEmail()) || userRepository.existsByUserName(applicationUser.getUserName()))
            throw new UserAlreadyExistsException();

        String encodedPassword = new BCryptPasswordEncoder().encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);
        Role userRole = roleRepository.findByRoleName("basic_user");
        applicationUser.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        if (applicationUser.getEmail().isEmpty() || applicationUser.getName().isEmpty() || applicationUser.getLastName().isEmpty())
            throw new MissingFieldsException();

        return userRepository.save(applicationUser);
    }

    @Override
    public List<String> getAllUsers() {
        return userRepository.getAllEntries();
    }
}
