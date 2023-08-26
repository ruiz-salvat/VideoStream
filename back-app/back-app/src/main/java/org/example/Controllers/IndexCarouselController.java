package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.DTOs.IndexCarouselDTO;
import org.example.Services.IIndexCarouselService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("index-carousel")
@CrossOrigin
@AllArgsConstructor
public class IndexCarouselController {

    private IIndexCarouselService indexCarouselService;

    @GetMapping(value = "image/{imageFilePath}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String imageFilePath) throws IOException {
        return indexCarouselService.getImage(imageFilePath);
    }

    @GetMapping(value = "{imageFilePath}")  // TODO: make it private
    public ResponseEntity<IndexCarouselDTO> getIndexCarousel(@PathVariable String imageFilePath) {
        return ResponseEntity.ok(indexCarouselService.getIndexCarousel(imageFilePath));
    }

    @GetMapping("all")  // TODO: make it private
    public ResponseEntity<List<IndexCarouselDTO>> getAllIndexCarousels() {
        return ResponseEntity.ok(indexCarouselService.getAllIndexCarousels());
    }

}
