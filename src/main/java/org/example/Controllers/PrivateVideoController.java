package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.DTOs.VideoDTO;
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
    public ResponseEntity<VideoDTO> setVideo(
            @RequestParam("slug") String slug,
            @RequestParam("title") String title,
            @RequestParam("synopsis") String synopsis,
            @RequestParam("description") String description,
            @RequestParam("category") Long category,
            @RequestParam("video_file") MultipartFile videoFile,
            @RequestParam("image_file") MultipartFile imageFile) {
        try {
            if (storageService.save(videoFile, slug, ".mp4") && storageService.save(imageFile, slug, ".jpg")) {
                VideoDTO videoDTO = videoService.saveVideo(slug, title, synopsis, description, category);
                return ResponseEntity.ok(videoDTO);
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
