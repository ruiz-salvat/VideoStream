package org.example.Services;

import org.example.DTOs.VideoDTO;
import org.springframework.core.io.InputStreamResource;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.List;

public interface IVideoService {

    Mono<byte[]> getVideo(String slug);

    Mono<byte[]> getVideoPart(String slug, double percentage);

    VideoDTO getVideoDetails(String slug);

    byte[] getVideoImage(String slug) throws IOException;

    VideoDTO saveVideo(String slug, String title, String synopsis, String description, Long categoryId, Long planId);

    void deleteVideo(String slug);

    List<VideoDTO> getAllVideos();

}
