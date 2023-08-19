package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTOs.IndexLayoutDTO;
import org.example.Entities.IndexLayout;
import org.example.Mappers.IMapper;
import org.example.Repositories.IIndexLayoutRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class IndexLayoutService implements IIndexLayoutService {

    private IIndexLayoutRepository indexLayoutRepository;

    private IMapper<IndexLayout, IndexLayoutDTO> indexLayoutMapper;

    @Override
    public IndexLayoutDTO getIndexLayout() {
        // TODO: select IndexLayout by priority

        List<IndexLayout> indexLayoutList = indexLayoutRepository.findAll();
        if (!indexLayoutList.isEmpty()) {
            IndexLayout indexLayout = indexLayoutList.get(0);
            return indexLayoutMapper.modelToDto(indexLayout);
        }

        throw new RuntimeException("IndexLayout list is empty"); // TODO: create exception
    }
}
