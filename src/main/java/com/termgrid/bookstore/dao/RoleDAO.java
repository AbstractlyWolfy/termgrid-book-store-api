package com.termgrid.bookstore.dao;

import com.termgrid.bookstore.model.role.Role;
import com.termgrid.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleDAO extends JpaRepository<Role, Integer> {

    /**
     * Find a role by its name
     * @param name - {@link String}
     * @return role - {@link User}
     */
    Role findByName(String name);

    /**
     * Find if a role exists by their name
     * @param name - {@link String}
     * @return exists - {@link Boolean}
     */
    Boolean existsByName(String name);
}
