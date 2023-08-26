package org.example.Mappers;

import lombok.NoArgsConstructor;
import org.example.DTOs.IndexCarouselDTO;
import org.example.Entities.IndexCarousel;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@NoArgsConstructor
public class IndexCarouselMapper implements IMapper<IndexCarousel, IndexCarouselDTO>  {


    @Override
    public IndexCarouselDTO modelToDto(IndexCarousel model) {
        return new IndexCarouselDTO(model.getImageFilePath(), model.getImageAlt(), model.getLink());
    }

    @Override
    public IndexCarousel dtoToModel(IndexCarouselDTO dto) throws NoSuchMethodException {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<IndexCarouselDTO> modelsToDtos(Iterable<IndexCarousel> models) {
        Iterator<IndexCarousel> it = models.iterator();
        List<IndexCarouselDTO> indexCarouselDTOS = new ArrayList<>();
        while (it.hasNext()) {
            indexCarouselDTOS.add(modelToDto(it.next()));
        }
        return indexCarouselDTOS;
    }

    @Override
    public List<IndexCarousel> dtosToModels(List<IndexCarouselDTO> dtos) throws NoSuchMethodException {
        throw new RuntimeException("Not implemented yet");
    }
}
