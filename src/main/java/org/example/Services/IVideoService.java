package org.example.Services;

import org.example.DTOs.VideoDTO;
import org.example.Entities.Video;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.List;

public interface IVideoService {

    Mono<byte[]> getVideo(String slug);

    VideoDTO getVideoDetails(String slug);

    byte[] getVideoImage(String slug) throws IOException;

    void saveVideo(String slug, String title, String synopsis, String description);

    void deleteVideo(String slug);

    List<VideoDTO> getAllVideos();

}
