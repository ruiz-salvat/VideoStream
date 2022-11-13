package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.Entities.Video;
import org.example.Exceptions.VideoAlreadyExistsException;
import org.example.Repositories.IVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoService implements IVideoService {

    @Autowired
    private ResourceLoader resourceLoader;
    private IVideoRepository videoRepository;
    private static final String FORMAT = "classpath:%s";

    @Override
    public Mono<Resource> getVideo(String title) {
        Video video = videoRepository.findByTitle(title);
        return Mono.fromSupplier(() -> this.resourceLoader.getResource(String.format(FORMAT, video.getFilePath())));
    }

    @Override
    public void saveVideo(String title, String description, String filePath) throws IOException {
        if (videoRepository.existsByTitle(title)) {
            throw new VideoAlreadyExistsException();
        }

        Video newVideo = new Video(title, description, filePath);
        videoRepository.save(newVideo);
    }

    @Override
    public List<String> getAllVideos() {
        return videoRepository.getAllEntries();
    }

}