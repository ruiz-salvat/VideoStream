package org.example.Services;

import org.example.DTOs.CategoryDTO;
import java.util.List;

public interface ICategoryService {

    CategoryDTO getCategoryDetails(Long id);

    CategoryDTO saveCategory(String name, String description);

    void deleteCategory(Long id);

    List<CategoryDTO> getAllCategories();

}
