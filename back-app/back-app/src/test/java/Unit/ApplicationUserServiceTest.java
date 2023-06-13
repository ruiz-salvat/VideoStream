package Unit;

import org.example.Entities.ApplicationUser;
import org.example.Entities.Role;
import org.example.Entities.Subscription;
import org.example.Exceptions.UserAlreadyExistsException;
import org.example.Exceptions.UserNotFoundException;
import org.example.Repositories.IRoleRepository;
import org.example.Repositories.IUserRepository;
import org.example.Services.IUserService;
import org.example.Services.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Util.Constants.*;
import static org.junit.Assert.assertEquals;

public class ApplicationUserServiceTest {

    @Mock
    private IUserRepository userRepository;
    @Mock
    private IRoleRepository roleRepository;
    @Rule // initializes mocks
    public MockitoRule rule = MockitoJUnit.rule();
    private IUserService userService;
    private ApplicationUser mockApplicationUser;

    @Before
    public void setUp() {
        userService = new UserService(userRepository, roleRepository);
//        userService = new UserService();

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("basic_user"));
        Subscription subscription = new Subscription();
        mockApplicationUser = new ApplicationUser(TEST_USERNAME, TEST_EMAIL, TEST_PASSWORD, TEST_NAME, TEST_LAST_NAME, TEST_ADDRESS, roles, subscription);

        Mockito.when(userRepository.findByEmail(TEST_EMAIL))
                .thenReturn(mockApplicationUser);
        Mockito.when(userRepository.existsByEmail(TEST_EMAIL))
                .thenReturn(true);
        Mockito.when(userRepository.findByUserName(TEST_USERNAME))
                .thenReturn(mockApplicationUser);
        Mockito.when(userRepository.existsByUserName(TEST_USERNAME))
                .thenReturn(true);
        List<String> userList = new ArrayList<String>();
        userList.add("test_user_1");
        userList.add("test_user_2");
        Mockito.when(userRepository.getAllEntries())
                .thenReturn(userList);
    }

    @Test
    public void getUserByEmail_ok() {
        userRepository.save(mockApplicationUser);

        ApplicationUser applicationUser = userService.getUserByEmail(TEST_EMAIL);

        assertEquals(TEST_EMAIL, applicationUser.getEmail());
        assertEquals(TEST_NAME, applicationUser.getName());
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserByEmail_notFound() {
        userRepository.save(mockApplicationUser);

        userService.getUserByEmail("wrong_email");
    }

    @Test
    public void getUserByUserName_ok() {
        userRepository.save(mockApplicationUser);

        ApplicationUser applicationUser = userService.getUserByUserName(TEST_USERNAME);

        assertEquals(TEST_EMAIL, applicationUser.getEmail());
        assertEquals(TEST_NAME, applicationUser.getName());
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserByUserName_notFound() {
        userRepository.save(mockApplicationUser);

        userService.getUserByUserName("wrong_username");
    }

    @Test
    public void saveUser_ok() {
        String newUsername = "new username";
        String newEmail = "new emails";
        String newName = "new name";
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("basic_user"));
        Subscription subscription = new Subscription();
        ApplicationUser newApplicationUser = new ApplicationUser(newUsername, newEmail, TEST_PASSWORD, newName, TEST_LAST_NAME, TEST_ADDRESS, roles, subscription);
        Mockito.when(userRepository.save(newApplicationUser))
                .thenReturn(newApplicationUser);

        ApplicationUser applicationUser = userService.saveUser(newApplicationUser);

        assertEquals(newUsername, applicationUser.getUserName());
        assertEquals(newEmail, applicationUser.getEmail());
        assertEquals(newName, applicationUser.getName());
        assertEquals(newApplicationUser, applicationUser);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void saveUser_alreadyExists() {
        userService.saveUser(mockApplicationUser);
    }

    @Test
    public void getAllUsers_listCountEqualsTwo() {
        List<String> users = userService.getAllUsers();

        assertEquals(2, users.size());
    }

}
