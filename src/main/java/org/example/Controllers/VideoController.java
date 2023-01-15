package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.DTOs.VideoDTO;
import org.example.Services.IVideoService;
import org.springframework.http.MediaType;
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

    private IVideoService videoService;

    @GetMapping(value = "{slug}", produces = "video/mp4")
    public Mono<byte[]> getVideo(@PathVariable String slug, @RequestHeader("Range") String range) {
        return videoService.getVideo(slug);
    }

    @GetMapping(value = "details/{slug}")
    public ResponseEntity<VideoDTO> getVideoDetails(@PathVariable String slug) {
        return ResponseEntity.ok(videoService.getVideoDetails(slug));
    }

    @GetMapping(value = "image/{slug}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable String slug) throws IOException {
        return videoService.getVideoImage(slug);
    }

    @GetMapping("all")
    public ResponseEntity<List<VideoDTO>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

}
