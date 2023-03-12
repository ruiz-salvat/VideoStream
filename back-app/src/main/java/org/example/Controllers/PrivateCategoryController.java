package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.DTOs.CategoryDTO;
import org.example.Services.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("private-category")
@CrossOrigin
@AllArgsConstructor
public class PrivateCategoryController {

    private ICategoryService categoryService;

    @PostMapping()
    public ResponseEntity<CategoryDTO> setCategory(
            @RequestParam("name") String name,
            @RequestParam("description") String description) {
        CategoryDTO categoryDTO = categoryService.saveCategory(name, description);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Long> deleteCategory(@PathVariable Long id) {
        // TODO: what happens with the videos related to this category?
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(id);
    }

}
