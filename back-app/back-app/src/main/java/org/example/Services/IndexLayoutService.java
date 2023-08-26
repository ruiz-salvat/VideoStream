package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTOs.IndexCarouselDTO;
import org.example.DTOs.IndexLayoutDTO;
import org.example.Entities.IndexCarousel;
import org.example.Entities.IndexLayout;
import org.example.Mappers.IMapper;
import org.example.Repositories.IIndexCarouselRepository;
import org.example.Repositories.IIndexLayoutRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class IndexLayoutService implements IIndexLayoutService {

    private IIndexLayoutRepository indexLayoutRepository;

    private IIndexCarouselRepository indexCarouselRepository;

    private IMapper<IndexLayout, IndexLayoutDTO> indexLayoutMapper;

    private IMapper<IndexCarousel, IndexCarouselDTO> indexCarouselMapper;

    @Override
    public IndexLayoutDTO getIndexLayout() {
        List<IndexLayout> indexLayoutList = indexLayoutRepository.findAll();
        IndexLayout biggestIndexLayout;
        if (!indexLayoutList.isEmpty()) {
            biggestIndexLayout = indexLayoutList.get(0);
            if (indexLayoutList.size() > 1) {
                for (int i = 1; i < indexLayoutList.size(); i++) {
                    if (indexLayoutList.get(i).getPriority() > biggestIndexLayout.getPriority())
                        biggestIndexLayout = indexLayoutList.get(i);
                }
            }

            IndexLayoutDTO indexLayoutDTO = indexLayoutMapper.modelToDto(biggestIndexLayout);

            List<IndexCarousel> indexCarousels = indexCarouselRepository.findAllByIndexLayout();
            List<IndexCarouselDTO> indexCarouselDTOS = indexCarouselMapper.modelsToDtos(indexCarousels);

            indexLayoutDTO.setIndexCarousels(indexCarouselDTOS);

            return indexLayoutDTO;
        }

        throw new RuntimeException("IndexLayout list is empty");
    }

    @Override
    public List<IndexLayoutDTO> getAllIndexLayouts() {
        return indexLayoutMapper.modelsToDtos(indexLayoutRepository.findAll());
    }
}
