package dev.Yass.to_do_list.repository;

import dev.Yass.to_do_list.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

    Categories findByName(String name);

    void deleteByName(String name);
    // You can add custom query methods here if needed
}
