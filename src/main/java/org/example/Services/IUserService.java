package org.example.Services;

import org.example.Entities.User;
import java.util.List;

public interface IUserService {

    User getUserByEmail(String email);

    User getUserByUserName(String userName);

    User saveUser(User user);

    List<String> getAllUsers();

}
