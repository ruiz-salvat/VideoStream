package Unit;

import org.example.Entities.Video;
import org.example.Exceptions.VideoAlreadyExistsException;
import org.example.Exceptions.VideoNotFoundException;
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

import static Util.Constants.*;
import static org.junit.Assert.*;

public class VideoServiceTest {

    @Autowired
    private Environment env;
    @Mock
    private IVideoRepository videoRepository;
    @Rule // initializes mocks
    public MockitoRule rule = MockitoJUnit.rule();
    public IVideoService videoService;
    private Video mockVideo;

    @Before
    public void setUp() {
        videoService = new VideoService(videoRepository, env);

        mockVideo = new Video(TEST_SLUG, TEST_TITLE, TEST_DESCRIPTION, TEST_SYNOPSIS, TEST_VIDEO_FILE_PATH, TEST_IMAGE_FILE_PATH);

        Mockito.when(videoRepository.findBySlug(TEST_SLUG))
                .thenReturn(mockVideo);
        Mockito.when(videoRepository.existsBySlug(TEST_SLUG))
                .thenReturn(true);
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
        Video video = videoService.getVideoDetails(TEST_SLUG);

        assertEquals(mockVideo, video);
    }

    @Test(expected = VideoNotFoundException.class)
    public void getVideoDetails_notFound() {
        videoService.getVideoDetails("wrong_slug");
    }

    @Test
    public void saveVideo_ok() {
        Video mockVideo = new Video("new_slug", TEST_TITLE, TEST_SYNOPSIS, TEST_DESCRIPTION, TEST_VIDEO_FILE_PATH, TEST_IMAGE_FILE_PATH);
        Mockito.when(videoRepository.findBySlug("new_slug"))
                .thenReturn(mockVideo);

        videoService.saveVideo("new_slug", TEST_TITLE, TEST_SYNOPSIS, TEST_DESCRIPTION);

        Video video = videoRepository.findBySlug("new_slug");

        assertEquals("new_slug", video.getSlug());
        assertEquals(TEST_TITLE, video.getTitle());
        assertEquals(TEST_DESCRIPTION, video.getDescription());
    }

    @Test(expected = VideoAlreadyExistsException.class)
    public void saveVideo_alreadyExists() {
        videoService.saveVideo(TEST_SLUG, TEST_TITLE, TEST_SYNOPSIS, TEST_DESCRIPTION);
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
