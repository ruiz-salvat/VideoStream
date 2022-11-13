package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.Services.VideoService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
            @RequestParam("file") MultipartFile file) throws IOException {

        videoService.saveVideo(title, description, file);
        return ResponseEntity.ok("Video saved successfully");
    }

    @GetMapping("{title}")
    public ResponseEntity<Resource> getVideoByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(new ByteArrayResource(videoService.getVideo(title).getData()));
    }

    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

}
