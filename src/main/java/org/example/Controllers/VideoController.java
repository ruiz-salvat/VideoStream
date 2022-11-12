package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.Services.VideoService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("video")
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
    public ResponseEntity<ByteArrayResource> getVideoByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(new ByteArrayResource(videoService.getVideo(title).getData()));
    }

    @GetMapping("/kek")
    public ResponseEntity<String> kek() {
        return ResponseEntity.ok("Keksimus prime");
    }

}
