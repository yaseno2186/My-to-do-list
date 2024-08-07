package dev.Yass.to_do_list.controller;

import dev.Yass.to_do_list.model.Categories;
import dev.Yass.to_do_list.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService; // Assuming you have a CategoryService

    @PostMapping("/create")
    public Categories createCategory(@RequestBody Categories category) {
        return categoryService.save(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Categories> getAllCategories() {
        return categoryService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

}
