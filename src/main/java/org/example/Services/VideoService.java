package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.Entities.Video;
import org.example.Exceptions.VideoAlreadyExistsException;
import org.example.Exceptions.VideoNotFoundException;
import org.example.Repositories.IVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoService implements IVideoService {

    private IVideoRepository videoRepository;

    @Autowired
    private Environment env;

    @Override
    public Mono<byte[]> getVideo(String slug) {
        if (!videoRepository.existsBySlug(slug))
            throw new VideoNotFoundException();

        Video video = videoRepository.findBySlug(slug);
        return Mono.fromSupplier(() -> {
            try {
                String fileFormat = env.getProperty("fileFormat");
                if (fileFormat == null || fileFormat.isEmpty())
                    throw new RuntimeException("empty property: fileFormat");
                return Files.readAllBytes(Paths.get(String.format(fileFormat, video.getFilePath())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Video getVideoDetails(String slug) {
        if (!videoRepository.existsBySlug(slug))
            throw new VideoNotFoundException();

        return videoRepository.findBySlug(slug);
    }

    @Override
    public void saveVideo(String slug, String title, String description) {
        if (videoRepository.existsBySlug(slug))
            throw new VideoAlreadyExistsException();

        String filePath = slug + ".mp4";

        Video newVideo = new Video(slug, title, description, filePath);
        videoRepository.save(newVideo);
    }

    @Override
    @Transactional
    public void deleteVideo(String slug) {
        if (!videoRepository.existsBySlug(slug))
            throw new VideoNotFoundException();

        videoRepository.deleteBySlug(slug);
    }

    @Override
    public List<Video> getAllVideos() {
        return (List<Video>) videoRepository.findAll();
    }

}