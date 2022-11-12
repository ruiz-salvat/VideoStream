package org.example.Services;

import org.example.Entities.Video;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface IVideoService {

    Video getVideo(String title);

    void saveVideo(String title, String description, MultipartFile file) throws IOException;

    List<String> getAllVideos();

}
