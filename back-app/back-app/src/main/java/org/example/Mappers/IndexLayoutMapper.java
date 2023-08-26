package org.example.Mappers;

import lombok.NoArgsConstructor;
import org.example.DTOs.IndexCarouselDTO;
import org.example.DTOs.IndexLayoutDTO;
import org.example.Entities.IndexCarousel;
import org.example.Entities.IndexLayout;
import org.example.Repositories.IIndexCarouselRepository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@NoArgsConstructor
public class IndexLayoutMapper implements IMapper<IndexLayout, IndexLayoutDTO> {

    @Override
    public IndexLayoutDTO modelToDto(IndexLayout model) {
        return new IndexLayoutDTO(model.getId(), model.getText1(), model.getText2(), model.getText3(), model.getText4(), null);
    }

    @Override
    public IndexLayout dtoToModel(IndexLayoutDTO dto) throws NoSuchMethodException {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<IndexLayoutDTO> modelsToDtos(Iterable<IndexLayout> models) {
        Iterator<IndexLayout> it = models.iterator();
        List<IndexLayoutDTO> indexLayoutDTOS = new ArrayList<>();
        while (it.hasNext()) {
            indexLayoutDTOS.add(modelToDto(it.next()));
        }
        return indexLayoutDTOS;
    }

    @Override
    public List<IndexLayout> dtosToModels(List<IndexLayoutDTO> dtos) throws NoSuchMethodException {
        throw new RuntimeException("Not implemented yet");
    }

}
