package Unit;

import org.example.DTOs.CategoryDTO;
import org.example.Entities.Category;
import org.example.Exceptions.CategoryAlreadyExistsException;
import org.example.Exceptions.CategoryNotFoundException;
import org.example.Mappers.CategoryMapper;
import org.example.Repositories.ICategoryRepository;
import org.example.Services.CategoryService;
import org.example.Services.ICategoryService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.Optional;

import static Util.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class CategoryServiceTest {

    @Mock
    private ICategoryRepository categoryRepository;
    @Rule // initializes mocks
    public MockitoRule rule = MockitoJUnit.rule();
    private ICategoryService categoryService;

    @Before
    public void setUp() {
        CategoryMapper categoryMapper = new CategoryMapper();
//        categoryService = new CategoryService(categoryRepository, categoryMapper);
         categoryService = new CategoryService();

        Category mockCategory = new Category(TEST_CATEGORY_NAME, TEST_CATEGORY_DESCRIPTION);

        Mockito.when(categoryRepository.existsById(TEST_CATEGORY_ID)).thenReturn(true);
        Mockito.when(categoryRepository.findById(TEST_CATEGORY_ID)).thenReturn(Optional.of(mockCategory));
        Mockito.when(categoryRepository.save(any())).thenReturn(mockCategory);
    }

    @Test
    public void getCategoryDetails_ok() {
        CategoryDTO categoryDTO = categoryService.getCategoryDetails(TEST_CATEGORY_ID);

        assertEquals(TEST_CATEGORY_NAME, categoryDTO.getName());
        assertEquals(TEST_CATEGORY_DESCRIPTION, categoryDTO.getDescription());
    }

    @Test(expected = CategoryNotFoundException.class)
    public void getCategoryDetails_notFound() {
        categoryService.getCategoryDetails(999L); // wrong id
    }

    @Test
    public void saveCategory_ok() {
        CategoryDTO categoryDTO = categoryService.saveCategory(TEST_CATEGORY_NAME, TEST_DESCRIPTION);

        assertEquals(TEST_CATEGORY_NAME, categoryDTO.getName());
        assertEquals(TEST_CATEGORY_DESCRIPTION, categoryDTO.getDescription());
    }

    @Test(expected = CategoryAlreadyExistsException.class)
    public void saveCategory_alreadyExists() {
        Mockito.when(categoryRepository.existsByName(TEST_CATEGORY_NAME)).thenReturn(true);

        categoryService.saveCategory(TEST_CATEGORY_NAME, TEST_DESCRIPTION);
    }

    @Test
    public void deleteCategory_ok() {
        categoryService.deleteCategory(TEST_CATEGORY_ID);
    }

    @Test(expected = CategoryNotFoundException.class)
    public void deleteCategory_notFound() {
        categoryService.deleteCategory(999L); // wrong id
    }
}
