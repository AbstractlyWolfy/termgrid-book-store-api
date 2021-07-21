package com.termgrid.bookstore.dao;

import com.termgrid.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {

    /**
     * Find a user by their username
     * @param username - {@link String}
     * @return user - {@link User}
     */
    Optional<User> findByUsername(String username);

    /**
     * Find if a user exists by their username
     * @param username - {@link String}
     * @return exists - {@link Boolean}
     */
    Boolean existsByUsername(String username);
}
