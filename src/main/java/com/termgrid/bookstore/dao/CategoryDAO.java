package com.termgrid.bookstore.dao;

import com.termgrid.bookstore.model.Category;
import com.termgrid.bookstore.model.Comment;
import com.termgrid.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

    /**
     * Find a category by name
     * @param name - {@link String}
     * @return Category - {@link Comment}
     */
    Category getCategoryByName(String name);
}
