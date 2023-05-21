package Integration;

import org.example.Entities.Video;
import org.example.Main;
import org.example.Repositories.IVideoRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static Util.Constants.*;
import static Util.Constants.TEST_SLUG;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integrationtest.properties")
public class NotLoggedInTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private IVideoRepository videoRepository;

    @Test
    public void post_video_found() throws Exception {
        mvc.perform(multipart("/private-video")
                        .file("file", "some txt".getBytes())
                        .param("slug", TEST_SLUG)
                        .param("title", TEST_TITLE)
                        .param("description", TEST_DESCRIPTION))
                .andExpect(status().isFound()); // redirect

        Video video = videoRepository.findBySlug(TEST_SLUG);

        assertNull(video);
    }

    @After
    public void cleanTestDatabase() {
        videoRepository.deleteAll();
    }

}
