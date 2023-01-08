package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.Exceptions.EmptyFileException;
import org.example.Services.IStorageService;
import org.example.Services.IVideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("private-video")
@CrossOrigin
@AllArgsConstructor
public class PrivateVideoController {

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

    @DeleteMapping(value = "{slug}")
    public ResponseEntity<String> deleteVideo(@PathVariable String slug) {
        try {
            if (storageService.delete(slug)) {
                videoService.deleteVideo(slug);
                return ResponseEntity.ok("Video deleted successfully");
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
