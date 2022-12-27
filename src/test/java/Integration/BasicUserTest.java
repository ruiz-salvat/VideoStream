package Integration;

import org.example.Main;
import org.example.Repositories.IUserRepository;
import org.example.Repositories.IVideoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integrationtest.properties")
public class BasicUserTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IVideoRepository videoRepository;

    @Before
    public void registerUser() throws Exception {
        String formData = "name=test_name&lastName=test_lastname&email=test%40email.com&userName=test_username&password=test_password";
        mvc.perform(post("/registration").contentType(APPLICATION_FORM_URLENCODED).content(formData)).andExpect(status().isOk());
    }

    @Test
    public void post_videoForm_responseStatus302() throws Exception {
        String formData = "file=test_byte_array&slug=test_slug&title=test_title&description=test_description";

        mvc.perform(post("/private-video").contentType(APPLICATION_FORM_URLENCODED).content(formData))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @After
    public void cleanTestDatabase() {
        userRepository.deleteAll();
        videoRepository.deleteAll();
    }

}
