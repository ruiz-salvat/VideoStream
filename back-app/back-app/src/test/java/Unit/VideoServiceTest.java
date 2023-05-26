package Unit;

import org.example.DTOs.VideoDTO;
import org.example.Entities.Category;
import org.example.Entities.Plan;
import org.example.Entities.Video;
import org.example.Exceptions.VideoAlreadyExistsException;
import org.example.Exceptions.VideoNotFoundException;
import org.example.Mappers.VideoMapper;
import org.example.Repositories.ICategoryRepository;
import org.example.Repositories.IVideoRepository;
import org.example.Services.IVideoService;
import org.example.Services.VideoService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Mono;
import java.util.Optional;

import static Util.Constants.*;
import static org.junit.Assert.*;

public class VideoServiceTest {

    @Autowired
    private Environment env;
    @Mock
    private IVideoRepository videoRepository;
    @Mock
    private ICategoryRepository categoryRepository;
    @Rule // initializes mocks
    public MockitoRule rule = MockitoJUnit.rule();
    public IVideoService videoService;
    private VideoDTO mockVideoDto;
    private Category mockCategory;

    @Before
    public void setUp() {
        VideoMapper videoMapper = new VideoMapper();
        videoService = new VideoService(videoRepository, categoryRepository, videoMapper, env);

        mockCategory = new Category(TEST_CATEGORY_NAME, TEST_CATEGORY_DESCRIPTION);
        Plan plan = new Plan();
        Video mockVideo = new Video(TEST_SLUG, TEST_TITLE, TEST_SYNOPSIS, TEST_DESCRIPTION, TEST_VIDEO_FILE_PATH, TEST_IMAGE_FILE_PATH, mockCategory, plan);
        mockVideoDto = new VideoDTO(TEST_SLUG, TEST_TITLE, TEST_SYNOPSIS, TEST_DESCRIPTION, TEST_CATEGORY_ID);

        Mockito.when(videoRepository.findBySlug(TEST_SLUG))
                .thenReturn(mockVideo);
        Mockito.when(videoRepository.existsBySlug(TEST_SLUG))
                .thenReturn(true);
        Mockito.when(categoryRepository.findById(TEST_CATEGORY_ID)).thenReturn(Optional.of(mockCategory));
    }

    @Test
    public void getVideo_ok() {
        Mono<byte[]> videoBytes = videoService.getVideo(TEST_SLUG);

        assertNotNull(videoBytes);
    }

    @Test(expected = VideoNotFoundException.class)
    public void getVideo_notFound() {
        videoService.getVideo("wrong_slug");
    }

    @Test
    public void getVideoDetails_ok() {
        VideoDTO videoDto = videoService.getVideoDetails(TEST_SLUG);

        assertEquals(mockVideoDto, videoDto);
    }

    @Test(expected = VideoNotFoundException.class)
    public void getVideoDetails_notFound() {
        videoService.getVideoDetails("wrong_slug");
    }

    @Test
    public void saveVideo_ok() { // TODO: refactor
        Plan plan = new Plan();
        Video mockVideo = new Video("new_slug", TEST_TITLE, TEST_SYNOPSIS, TEST_DESCRIPTION, TEST_VIDEO_FILE_PATH, TEST_IMAGE_FILE_PATH, mockCategory, plan);
        Mockito.when(videoRepository.findBySlug("new_slug"))
                .thenReturn(mockVideo);
        Mockito.when(videoRepository.save(mockVideo)).thenReturn(mockVideo);

        videoService.saveVideo("new_slug", TEST_TITLE, TEST_SYNOPSIS, TEST_DESCRIPTION, TEST_CATEGORY_ID);

        Video video = videoRepository.findBySlug("new_slug");

        assertEquals("new_slug", video.getSlug());
        assertEquals(TEST_TITLE, video.getTitle());
        assertEquals(TEST_DESCRIPTION, video.getDescription());
    }

    @Test(expected = VideoAlreadyExistsException.class)
    public void saveVideo_alreadyExists() {
        videoService.saveVideo(TEST_SLUG, TEST_TITLE, TEST_SYNOPSIS, TEST_DESCRIPTION, TEST_CATEGORY_ID);
    }

    @Test
    public void deleteVideo_ok() {
        videoService.deleteVideo(TEST_SLUG);
    }

    @Test(expected = VideoNotFoundException.class)
    public void deleteVideo_notFound() {
        videoService.deleteVideo("wrong_slug");
    }
}
