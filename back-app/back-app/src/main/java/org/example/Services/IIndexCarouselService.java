package org.example.Services;

import org.example.DTOs.IndexCarouselDTO;

import java.io.IOException;
import java.util.List;

public interface IIndexCarouselService {

    IndexCarouselDTO saveCarousel(String imageFilePath, String imageAlt, String link, long indexLayoutId);

    IndexCarouselDTO getIndexCarousel(String imageFilePath);

    byte[] getImage(String imageFilePath) throws IOException;

    List<IndexCarouselDTO> getAllIndexCarousels();

}
