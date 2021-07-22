package com.termgrid.bookstore.dao;

import com.termgrid.bookstore.model.Book;
import com.termgrid.bookstore.model.Review;
import com.termgrid.bookstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReviewDAO extends PagingAndSortingRepository<Review, Integer> {

    /**
     * Find all reviews by author
     * @param author - {@link String}
     * @return review - {@link Review}
     */
    Page<Review> getAllByAuthor(User author, Pageable pageable);

    /**
     * Find all reviews by author
     * @param book - {@link Book}
     * @return review - {@link Review}
     */
    Page<Review> getAllByBook(Book book, Pageable pageable);
}
