package Integration;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.example.Entities.Video;
import org.example.Main;
import org.example.Repositories.ICategoryRepository;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static Util.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integrationtest.properties")
public class AdminUserTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private IVideoRepository videoRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    private SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor mockUser;

    @Before
    public void createAdminUser() {
        GrantedAuthority authority = new SimpleGrantedAuthority("admin");
        mockUser = user(TEST_USERNAME).authorities(authority);
    }

    @Test
    public void post_video_ok() throws Exception {
        mvc.perform(multipart("/private-category")
                        .param("name", TEST_CATEGORY_NAME)
                        .param("description", TEST_CATEGORY_DESCRIPTION)
                        .with(mockUser))
                .andExpect(status().isOk());

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/category/all")).andReturn();

        String categoryResponse = result.getResponse().getContentAsString();
        JSONParser parser = new JSONParser();
        JSONArray jsonArr = (JSONArray) parser.parse(categoryResponse);
        JSONObject jsonObj = (JSONObject) jsonArr.get(0);
        Integer id = (Integer) jsonObj.get("id");

        mvc.perform(multipart("/private-video")
                        .file("video_file", "some txt".getBytes())
                        .file("image_file", "some text".getBytes())
                        .param("slug", TEST_SLUG)
                        .param("title", TEST_TITLE)
                        .param("synopsis", TEST_SYNOPSIS)
                        .param("description", TEST_DESCRIPTION)
                        .param("category", id.toString())
                        .with(mockUser))
                    .andExpect(status().isOk());

        Video video = videoRepository.findBySlug(TEST_SLUG);

        assertNotNull(video);
        assertEquals(TEST_TITLE, video.getTitle());
    }

    @After
    public void cleanTestDatabase() {
        videoRepository.deleteAll();
        categoryRepository.deleteAll();
    }

}
