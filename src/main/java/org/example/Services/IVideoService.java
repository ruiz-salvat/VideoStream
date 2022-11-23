package org.example.Services;

import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.List;

public interface IVideoService {

    Mono<Resource> getVideo(String title);

    String getVideoDescription(String title);

    void saveVideo(String title, String description, String filePath) throws IOException;

    List<String> getAllVideos();

}
