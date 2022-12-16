package org.example.Services;

import org.example.Entities.Video;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.List;

public interface IVideoService {

    Mono<Resource> getVideo(String slug);

    String getVideoDescription(String slug);

    void saveVideo(String slug, String title, String description) throws IOException;

    List<Video> getAllVideos();

}
