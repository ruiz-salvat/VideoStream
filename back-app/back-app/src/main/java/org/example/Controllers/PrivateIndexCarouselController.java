package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.DTOs.IndexCarouselDTO;
import org.example.Services.IIndexCarouselService;
import org.example.Services.IStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import static org.example.Util.Constants.IMAGE_FILE_EXTENSION;

@RestController
@RequestMapping("private-index-carousel")
@CrossOrigin
@AllArgsConstructor
public class PrivateIndexCarouselController {

    private IIndexCarouselService indexCarouselService;

    private IStorageService storageService;

    @PostMapping()
    public ResponseEntity<IndexCarouselDTO> setIndexCarousel(
            @RequestParam("name") String name,
            @RequestParam("image_alt") String imageAlt,
            @RequestParam("link") String link,
            @RequestParam("index_layout") Long indexLayout,
            @RequestParam("image_file") MultipartFile imageFile
    ) {
        try {
            if (storageService.save(imageFile, name, IMAGE_FILE_EXTENSION)) {
                IndexCarouselDTO indexCarouselDTO = indexCarouselService.saveCarousel(name + IMAGE_FILE_EXTENSION, imageAlt, link, indexLayout);
                return ResponseEntity.ok(indexCarouselDTO);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
