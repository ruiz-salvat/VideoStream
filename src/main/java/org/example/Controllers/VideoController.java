package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.Services.IStorageService;
import org.example.Services.IVideoService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("video")
@CrossOrigin
@AllArgsConstructor
public class VideoController {

    private IVideoService videoService;
    private IStorageService storageService;

    @PostMapping()
    public ResponseEntity<String> setVideo(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) {

        if (storageService.save(file, title)) {
            try {
                videoService.saveVideo(title, description);
            } catch (IOException e) {
                throw new RuntimeException(e); // TODO: throw custom exception
            }

            return ResponseEntity.ok("Video saved successfully");
        }

        throw new RuntimeException("Error saving the video file");
    }

    @GetMapping(value = "{title}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String title, @RequestHeader("Range") String range) {
        return videoService.getVideo(title);
    }

    @GetMapping(value = "description/{title}")
    public String getVideoDescription(@PathVariable String title) {
        return videoService.getVideoDescription(title);
    }

    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

}
