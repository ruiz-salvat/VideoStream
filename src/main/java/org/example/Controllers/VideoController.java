package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.Entities.Video;
import org.example.Exceptions.EmptyFileException;
import org.example.Services.IStorageService;
import org.example.Services.IVideoService;
import org.springframework.http.HttpStatus;
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
            @RequestParam("slug") String slug,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) {
        try {
            if (storageService.save(file, slug)) {
                videoService.saveVideo(slug, title, description);
                return ResponseEntity.ok("Video saved successfully");
            }
        } catch (EmptyFileException | IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "{slug}", produces = "video/mp4")
    public Mono<byte[]> getVideo(@PathVariable String slug, @RequestHeader("Range") String range) {
        return videoService.getVideo(slug);
    }

    @GetMapping(value = "details/{slug}")
    public ResponseEntity<Video> getVideoDetails(@PathVariable String slug) {
        return ResponseEntity.ok(videoService.getVideoDetails(slug));
    }

    @GetMapping("all")
    public ResponseEntity<List<Video>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

}
