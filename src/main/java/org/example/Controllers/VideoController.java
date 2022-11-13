package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.Services.VideoService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("video")
@CrossOrigin
@AllArgsConstructor
public class VideoController {

    private VideoService videoService;

    @PostMapping()
    public ResponseEntity<String> setVideo(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("filePath") String filePath) throws IOException {

        videoService.saveVideo(title, description, filePath);
        return ResponseEntity.ok("Video saved successfully");
    }

    @GetMapping(value = "{title}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String title, @RequestHeader("Range") String range) {
        return videoService.getVideo(title);
    }

    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

}
