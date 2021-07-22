package com.termgrid.bookstore.dao;

import com.termgrid.bookstore.model.Book;
import com.termgrid.bookstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookDAO extends PagingAndSortingRepository<Book, Integer> {


    /**
     * Find a book by its id
     * @param id - {@link Integer}
     * @return book - {@link Book}
     */
    Page<Book> findById(int id, Pageable pageable);

    /**
     * Find a book by its name
     * @param name - {@link String}
     * @return book - {@link Book}
     */
    Page<Book> findByName(String name, Pageable pageable);

    /**
     * Find a book by its unique slug
     * @param slug - {@link String}
     * @return book - {@link Book}
     */
    Page<Book> findBySlug(String slug, Pageable pageable);
}
