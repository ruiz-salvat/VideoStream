package org.example.Mappers;

import lombok.NoArgsConstructor;
import org.example.DTOs.CategoryDTO;
import org.example.Entities.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@NoArgsConstructor
public class CategoryMapper implements IMapper<Category, CategoryDTO> {
    @Override
    public CategoryDTO modelToDto(Category model) {
        return new CategoryDTO(model.getId(), model.getName(), model.getDescription());
    }

    @Override
    public Category dtoToModel(CategoryDTO dto) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<CategoryDTO> modelsToDtos(Iterable<Category> models) {
        Iterator<Category> it = models.iterator();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        while (it.hasNext()) {
            categoryDTOS.add(modelToDto(it.next()));
        }
        return categoryDTOS;
    }

    @Override
    public List<Category> dtosToModels(List<CategoryDTO> dtos) {
        throw new RuntimeException("Not implemented yet");
    }
}
