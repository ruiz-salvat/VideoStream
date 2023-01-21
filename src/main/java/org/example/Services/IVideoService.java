package org.example.Services;

import org.example.DTOs.VideoDTO;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.List;

public interface IVideoService {

    Mono<byte[]> getVideo(String slug);

    VideoDTO getVideoDetails(String slug);

    byte[] getVideoImage(String slug) throws IOException;

    VideoDTO saveVideo(String slug, String title, String synopsis, String description, Long categoryId);

    void deleteVideo(String slug);

    List<VideoDTO> getAllVideos();

}
