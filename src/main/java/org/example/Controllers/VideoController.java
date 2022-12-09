package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.Exceptions.EmptyFileException;
import org.example.Services.IStorageService;
import org.example.Services.IVideoService;
import org.springframework.core.io.Resource;
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
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) {
        try {
            if (storageService.save(file, title)) {
                videoService.saveVideo(title, description);
                return ResponseEntity.ok("Video saved successfully");
            }
        } catch (EmptyFileException | IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
