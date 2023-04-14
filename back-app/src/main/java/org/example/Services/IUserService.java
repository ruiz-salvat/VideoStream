package org.example.Services;

import org.example.Entities.ApplicationUser;

import java.util.List;

public interface IUserService {

    ApplicationUser getUserByEmail(String email);

    ApplicationUser getUserByUserName(String userName);

    ApplicationUser saveUser(ApplicationUser applicationUser);

    List<String> getAllUsers();

}
