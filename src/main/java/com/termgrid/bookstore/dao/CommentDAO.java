package com.termgrid.bookstore.dao;

import com.termgrid.bookstore.model.Comment;
import com.termgrid.bookstore.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentDAO extends PagingAndSortingRepository<Comment, Integer> {
    Page<Comment> findAll(Pageable pageable);

    Page<Comment> getAllByReview(Review review, Pageable pageable);
}
