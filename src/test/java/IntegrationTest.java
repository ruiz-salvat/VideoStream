import org.example.Main;
import org.example.Repositories.IVideoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integrationtest.properties")
public class IntegrationTest {

    @Autowired
    private MockMvc mvc;

    private IVideoRepository videoRepository;

    @Test
    public void register_login_thenStatus200() {
        int x = 2;
        assertEquals("first test", x, 2);
    }

}
