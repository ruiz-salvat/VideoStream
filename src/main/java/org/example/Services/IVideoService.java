package org.example.Services;

import org.example.Entities.Video;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IVideoService {

    Mono<byte[]> getVideo(String slug);

    Video getVideoDetails(String slug);

    byte[] getVideoImage(String slug) throws IOException;

    void saveVideo(String slug, String title, String description);

    void deleteVideo(String slug);

    List<Video> getAllVideos();

}
