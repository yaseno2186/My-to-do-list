package dev.Yass.to_do_list.controller;

import dev.Yass.to_do_list.model.Categories;
import dev.Yass.to_do_list.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService; // Assuming you have a CategoryService

    @PostMapping("/create")
    public Categories createCategory(@RequestBody Categories category) {
        return categoryService.save(category);
    }

    @GetMapping(value = {"/{id}", "/{name}"})
    public Categories getCategory(@PathVariable Long id, @PathVariable String name) {
        if (id == 0) {
            return categoryService.findByName(name);
        }
        return categoryService.findById(id).get();
    }


    @GetMapping("/all")
    public List<Categories> getAllCategories() {
        return categoryService.findAll();
    }

    @DeleteMapping(value = {"/{id}", "/{name}"})
    public void deleteCategoryById(@PathVariable Long id, String name) {
        if (id == 0) {
            categoryService.deleteByName(name);
        }
        categoryService.deleteById(id);
    }

    @PutMapping(value = {"/Rename/{id}", "/Rename/{name}"})
    public Categories updateCategoryName(@PathVariable String name, @RequestParam String newName, @RequestParam Long id) {
        if (id == 0) {
            Categories category = categoryService.findByName(name);
            category.setName(newName);
            return categoryService.save(category);
        } else {
            Optional<Categories> category = categoryService.findById(id);
            category.get().setName(newName);
            return categoryService.save(category.get());
        }
    }
}
