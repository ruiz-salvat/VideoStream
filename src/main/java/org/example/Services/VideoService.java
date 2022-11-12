package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.Entities.Video;
import org.example.Exceptions.VideoAlreadyExistsException;
import org.example.Exceptions.VideoNotFoundException;
import org.example.Repositories.IVideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoService implements IVideoService {

    private IVideoRepository videoRepository;

    @Override
    public Video getVideo(String title) {
        if (!videoRepository.existsByTitle(title))
            throw new VideoNotFoundException();

        return videoRepository.findByTitle(title);
    }

    @Override
    public void saveVideo(String title, String description, MultipartFile file) throws IOException {
        if (videoRepository.existsByTitle(title)) {
            throw new VideoAlreadyExistsException();
        }

        Video newVideo = new Video(title, description, file.getBytes());
        videoRepository.save(newVideo);
    }

    @Override
    public List<String> getAllVideos() {
        return videoRepository.getAllEntries();
    }

}