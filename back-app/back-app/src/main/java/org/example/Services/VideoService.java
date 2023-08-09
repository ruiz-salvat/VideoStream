package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTOs.VideoDTO;
import org.example.Entities.Category;
import org.example.Entities.Plan;
import org.example.Entities.Video;
import org.example.Exceptions.CategoryNotFoundException;
import org.example.Exceptions.PlanNotFoundException;
import org.example.Exceptions.VideoAlreadyExistsException;
import org.example.Exceptions.VideoNotFoundException;
import org.example.Mappers.IMapper;
import org.example.Repositories.ICategoryRepository;
import org.example.Repositories.IPlanRepository;
import org.example.Repositories.IVideoRepository;
import org.mp4parser.Container;
import org.mp4parser.muxer.Movie;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.builder.DefaultMp4Builder;
import org.mp4parser.muxer.container.mp4.MovieCreator;
import org.mp4parser.muxer.tracks.AppendTrack;
import org.mp4parser.muxer.tracks.ClippedTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import static org.example.Util.Constants.*;

@Service
@AllArgsConstructor
public class VideoService implements IVideoService {

    private IVideoRepository videoRepository;

    private IPlanRepository planRepository;

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
    public Mono<byte[]> getVideoPart(String slug, double percentage) {
        if (!videoRepository.existsBySlug(slug))
            throw new VideoNotFoundException();

        Video video = videoRepository.findBySlug(slug);
        return Mono.fromSupplier(() -> {
            try {
                Movie movie = MovieCreator.build(String.valueOf(Paths.get(String.format(getFileFormat(), video.getVideoFilePath()))));
                Movie output = new Movie();

                Track videoTrack = movie.getTracks().get(0);
                Track audioTrack = movie.getTracks().get(1);

                int videoSampleSize = videoTrack.getSamples().size();
                int audioSampleSize = audioTrack.getSamples().size();

                double startVideoSample = videoSampleSize * percentage;
                long startVideoSampleLong = (long)startVideoSample;

                double startAudioSample = audioSampleSize * percentage;
                long startAudioSampleLong = (long)startAudioSample;

                output.addTrack(new AppendTrack(new ClippedTrack(videoTrack, startVideoSampleLong, videoTrack.getSamples().size())));
                output.addTrack(new AppendTrack(new ClippedTrack(audioTrack, startAudioSampleLong, audioTrack.getSamples().size())));

                Container out = new DefaultMp4Builder().build(output);
                File tempFile = new File(String.valueOf(Paths.get(String.format(getFileFormat(), TEMP_FILE_OUTPUT_NAME))));
                FileChannel fc = new FileOutputStream(tempFile).getChannel();

                out.writeContainer(fc);
                fc.close();

                return Files.readAllBytes(tempFile.toPath());
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
    public VideoDTO saveVideo(String slug, String title, String synopsis, String description, Long categoryId, Long planId) {
        if (videoRepository.existsBySlug(slug))
            throw new VideoAlreadyExistsException();

        String videoFilePath = slug + VIDEO_FILE_EXTENSION;
        String imageFilePath = slug + IMAGE_FILE_EXTENSION;

        Category category;
        if (categoryRepository.findById(categoryId).isPresent())
            category = categoryRepository.findById(categoryId).get();
        else
            throw new CategoryNotFoundException();

        Plan plan;
        if (planRepository.findById(planId).isPresent())
            plan = planRepository.findById(planId).get();
        else
            throw new PlanNotFoundException();

        Video newVideo = new Video(slug, title, synopsis, description, videoFilePath, imageFilePath, false, category, plan);
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
        return videoMapper.modelsToDtos(videoRepository.findByIsInfoVideoIsFalse());
    }

}