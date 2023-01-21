package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTOs.VideoDTO;
import org.example.Entities.Category;
import org.example.Entities.Video;
import org.example.Exceptions.CategoryNotFoundException;
import org.example.Exceptions.VideoAlreadyExistsException;
import org.example.Exceptions.VideoNotFoundException;
import org.example.Mappers.IMapper;
import org.example.Repositories.ICategoryRepository;
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

    private ICategoryRepository categoryRepository;

    private IMapper<Video, VideoDTO> videoMapper;

    @Autowired
    private Environment env;

    private String getFileFormat() {
        String fileFormat = env.getProperty("fileFormat");
        if (fileFormat == null || fileFormat.isEmpty())
            throw new RuntimeException("empty property: fileFormat");
        return fileFormat;
    }

    @Override
    public Mono<byte[]> getVideo(String slug) {
        if (!videoRepository.existsBySlug(slug))
            throw new VideoNotFoundException();

        Video video = videoRepository.findBySlug(slug);
        return Mono.fromSupplier(() -> {
            try {
                return Files.readAllBytes(Paths.get(String.format(getFileFormat(), video.getVideoFilePath())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public VideoDTO getVideoDetails(String slug) {
        if (!videoRepository.existsBySlug(slug))
            throw new VideoNotFoundException();

        Video video = videoRepository.findBySlug(slug);
        return videoMapper.modelToDto(video);
    }

    @Override
    public byte[] getVideoImage(String slug) throws IOException {
        Video video = videoRepository.findBySlug(slug);
        return Files.readAllBytes(Paths.get(String.format(getFileFormat(), video.getImageFilePath())));
    }

    @Override
    public VideoDTO saveVideo(String slug, String title, String synopsis, String description, Long categoryId) {
        if (videoRepository.existsBySlug(slug))
            throw new VideoAlreadyExistsException();

        String videoFilePath = slug + ".mp4";
        String imageFilePath = slug + ".jpg"; // TODO: handle different formats

        Category category;
        if (categoryRepository.findById(categoryId).isPresent())
            category = categoryRepository.findById(categoryId).get();
        else
            throw new CategoryNotFoundException();

        Video newVideo = new Video(slug, title, synopsis, description, videoFilePath, imageFilePath, category);
        Video video = videoRepository.save(newVideo);
        return videoMapper.modelToDto(video);
    }

    @Override
    @Transactional
    public void deleteVideo(String slug) {
        if (!videoRepository.existsBySlug(slug))
            throw new VideoNotFoundException();

        videoRepository.deleteBySlug(slug);
    }

    @Override
    public List<VideoDTO> getAllVideos() {
        return videoMapper.modelsToDtos(videoRepository.findAll());
    }

}