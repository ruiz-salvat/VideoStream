package Integration;

import org.example.Entities.Role;
import org.example.Entities.User;
import org.example.Main;
import org.example.Repositories.IRoleRepository;
import org.example.Repositories.IUserRepository;
import org.example.Repositories.IVideoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integrationtest.properties")
public class AdminUserTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IVideoRepository videoRepository;

    @Before
    public void createAdminUser() throws Exception {
        // create admin user
        Role role = roleRepository.findByRoleName("admin");
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        String encodedPassword = new BCryptPasswordEncoder().encode("testPassword");
        User user = new User("testUserName", "test@email.com", encodedPassword, "testName", "testLastName", null, roles);
        userRepository.save(user);

        // log in as admin
        String formData = "user_name=testUserName&password=testPassword";
        mvc.perform(post("/login").contentType(APPLICATION_FORM_URLENCODED).content(formData))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/upload-video"));
    }

    @Test
    public void post_videoForm_responseStatus200() throws Exception {
        String formData = "file=test_byte_array&slug=test_slug&title=test_title&description=test_description";

        mvc.perform(post("/private-video").contentType(APPLICATION_FORM_URLENCODED).content(formData)).andExpect(status().isOk());
    }

    @After
    public void cleanTestDatabase() {
        userRepository.deleteAll();
        videoRepository.deleteAll();
    }

}
