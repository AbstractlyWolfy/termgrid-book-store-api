package com.termgrid.bookstore.dao;

import com.termgrid.bookstore.model.Book;
import com.termgrid.bookstore.model.Review;
import com.termgrid.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDAO extends JpaRepository<Review, Integer> {

    /**
     * Find all reviews by author
     * @param author - {@link String}
     * @return review - {@link Review}
     */
    Review getAllByAuthor(User author);

    /**
     * Find all reviews by author
     * @param book - {@link Book}
     * @return review - {@link Review}
     */
    Review getAllByBook(Book book);
}
