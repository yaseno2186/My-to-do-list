package dev.Yass.to_do_list.service;

import dev.Yass.to_do_list.model.Categories;
import dev.Yass.to_do_list.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Categories save(Categories category) {
        return categoryRepository.save(category);
    }

    public Optional<Categories> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Categories> findAll() {
        return categoryRepository.findAll();
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public void createDefaultMainCategory() {
        Categories defaultCategories = new Categories();
        defaultCategories.setName("Tasks");

        // Save the default main category "Tasks" in the database
        categoryRepository.save(defaultCategories);
    }

    // Other methods for category management
    // You can add additional methods for updating, deleting, and managing categories as needed
}
