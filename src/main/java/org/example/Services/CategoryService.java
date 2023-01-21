package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTOs.CategoryDTO;
import org.example.Entities.Category;
import org.example.Exceptions.CategoryAlreadyExistsException;
import org.example.Exceptions.CategoryNotFoundException;
import org.example.Mappers.CategoryMapper;
import org.example.Mappers.IMapper;
import org.example.Repositories.ICategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private ICategoryRepository categoryRepository;

    private IMapper<Category, CategoryDTO> categoryMapper;

    @Override
    public CategoryDTO getCategoryDetails(Long id) {
        if (!categoryRepository.existsById(id))
            throw new CategoryNotFoundException();

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return categoryMapper.modelToDto(category);
        } else {
            throw new RuntimeException("Error retrieving category");
        }
    }

    @Override
    public CategoryDTO saveCategory(String name, String description) {
        if (categoryRepository.existsByName(name))
            throw new CategoryAlreadyExistsException();

        Category newCategory = new Category(name, description);
        Category category = categoryRepository.save(newCategory);
        return categoryMapper.modelToDto(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id))
            throw new CategoryNotFoundException();

        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryMapper.modelsToDtos(categoryRepository.findAll());
    }
}
