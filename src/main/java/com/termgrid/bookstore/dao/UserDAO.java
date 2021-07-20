package com.termgrid.bookstore.dao;

import com.termgrid.bookstore.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {

    /**
     * Find a user by their email address
     * @param emailAddress - {@link String}
     * @return user - {@link User}
     */
    User findByEmail(String emailAddress);
}
