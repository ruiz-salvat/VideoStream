package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.Entities.Video;
import org.example.Exceptions.VideoAlreadyExistsException;
import org.example.Exceptions.VideoNotFoundException;
import org.example.Repositories.IVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoService implements IVideoService {

    @Autowired
    private ResourceLoader resourceLoader;
    private IVideoRepository videoRepository;
    private static final String FORMAT = "classpath:%s";

    @Override
    public Mono<Resource> getVideo(String slug) {
        if (!videoRepository.existsBySlug(slug))
            throw new VideoNotFoundException();

        Video video = videoRepository.findBySlug(slug);
        return Mono.fromSupplier(() -> this.resourceLoader.getResource(String.format(FORMAT, video.getFilePath())));
    }

    @Override
    public String getVideoDescription(String slug) {
        if (!videoRepository.existsBySlug(slug))
             throw new VideoNotFoundException();

        Video video = videoRepository.findBySlug(slug);
        return video.getDescription();
    }

    @Override
    public void saveVideo(String slug, String title, String description) {
        if (videoRepository.existsBySlug(slug))
            throw new VideoAlreadyExistsException();

        String filePath = "data/" + slug + ".mp4";

        Video newVideo = new Video(slug, title, description, filePath);
        videoRepository.save(newVideo);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

}