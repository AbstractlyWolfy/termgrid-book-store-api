package com.termgrid.bookstore.dao;

import com.termgrid.bookstore.model.Comment;
import com.termgrid.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, Integer> {

    /**
     * Find all comments by author
     * @param author - {@link String}
     * @return comment - {@link Comment}
     */
    Comment getAllByAuthor(User author);
}
