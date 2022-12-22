package org.example.Services;

import org.example.Entities.Video;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.List;

public interface IVideoService {

    Mono<byte[]> getVideo(String slug);

    Video getVideoDetails(String slug);

    void saveVideo(String slug, String title, String description) throws IOException;

    List<Video> getAllVideos();

}
