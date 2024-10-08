package Integration;

import org.example.Entities.Video;
import org.example.Main;
import org.example.Repositories.IVideoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static Util.Constants.*;
import static org.junit.Assert.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integrationtest.properties")
public class BasicApplicationUserTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private IVideoRepository videoRepository;
    private SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor mockUser;

    @Before
    public void createBasicUser() {
        GrantedAuthority authority = new SimpleGrantedAuthority("basic_user");
        mockUser = user(TEST_USERNAME).authorities(authority);
    }

    @Test
    public void post_video_forbidden() throws Exception {
        mvc.perform(multipart("/private-video")
                        .file("file", "some txt".getBytes())
                        .param("slug", TEST_SLUG)
                        .param("title", TEST_TITLE)
                        .param("description", TEST_DESCRIPTION)
                        .with(mockUser))
                    .andExpect(status().isForbidden());

        Video video = videoRepository.findBySlug(TEST_SLUG);

        assertNull(video);
    }

    @After
    public void cleanTestDatabase() {
        videoRepository.deleteAll();
    }

}
