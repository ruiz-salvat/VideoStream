package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTOs.IndexCarouselDTO;
import org.example.Entities.IndexCarousel;
import org.example.Entities.IndexLayout;
import org.example.Exceptions.ImageAlreadyExistsException;
import org.example.Exceptions.IndexLayoutNotFoundException;
import org.example.Mappers.IMapper;
import org.example.Repositories.IIndexCarouselRepository;
import org.example.Repositories.IIndexLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class IndexCarouselService implements IIndexCarouselService {

    private IIndexCarouselRepository indexCarouselRepository;

    private IIndexLayoutRepository indexLayoutRepository;

    private IMapper<IndexCarousel, IndexCarouselDTO> indexCarouselMapper;

    @Autowired
    private Environment env;

    @Override
    public IndexCarouselDTO saveCarousel(String imageFilePath, String imageAlt, String link, long indexLayoutId) {
        if (indexCarouselRepository.existsByImageFilePath(imageFilePath))
            throw new ImageAlreadyExistsException();

        IndexLayout indexLayout;
        if (indexLayoutRepository.findById(indexLayoutId).isPresent())
            indexLayout = indexLayoutRepository.findById(indexLayoutId).get();
        else
            throw new IndexLayoutNotFoundException();

        IndexCarousel newIndexCarousel = new IndexCarousel(imageFilePath, imageAlt, link, indexLayout);
        IndexCarousel indexCarousel = indexCarouselRepository.save(newIndexCarousel);

        return indexCarouselMapper.modelToDto(indexCarousel);
    }

    @Override
    public IndexCarouselDTO getIndexCarousel(String imageFilePath) {
        IndexCarousel indexCarousel = indexCarouselRepository.findByImageFilePath(imageFilePath);
        return indexCarouselMapper.modelToDto(indexCarousel);
    }

    private String getFileFormat() {
        String fileFormat = env.getProperty("fileFormat");
        if (fileFormat == null || fileFormat.isEmpty())
            throw new RuntimeException("empty property: fileFormat");
        return fileFormat;
    }

    @Override
    public byte[] getImage(String imageFilePath) throws IOException {
        return Files.readAllBytes(Paths.get(String.format(getFileFormat(), imageFilePath)));
    }

    @Override
    public List<IndexCarouselDTO> getAllIndexCarousels() {
        return indexCarouselMapper.modelsToDtos(indexCarouselRepository.findAll());
    }
}
