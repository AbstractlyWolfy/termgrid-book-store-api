package com.termgrid.bookstore.dao;

import com.termgrid.bookstore.model.Book;
import com.termgrid.bookstore.model.User;
import org.springframework.data.repository.CrudRepository;

public interface BookDAO extends CrudRepository<Book, Integer> {

    /**
     * Find a book by its name
     * @param name - {@link String}
     * @return book - {@link Book}
     */
    Book findByName(String name);
}
