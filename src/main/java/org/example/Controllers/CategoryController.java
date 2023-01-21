package org.example.Controllers;

import lombok.AllArgsConstructor;
import org.example.DTOs.CategoryDTO;
import org.example.Services.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("category")
@CrossOrigin
@AllArgsConstructor
public class CategoryController {

    private ICategoryService categoryService;

    @GetMapping("all")
    public ResponseEntity<List<CategoryDTO>> getAllVideos() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}
