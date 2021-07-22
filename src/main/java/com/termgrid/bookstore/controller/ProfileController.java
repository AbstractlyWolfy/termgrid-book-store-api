package com.termgrid.bookstore.controller;

import com.termgrid.bookstore.controller.response.GenericResponse;
import com.termgrid.bookstore.dao.UserDAO;
import com.termgrid.bookstore.model.User;
import com.termgrid.bookstore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/v1/profile")
public class ProfileController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    JwtUtil jwtUtil;

    /**
     * Get a profile by its username.
     * @param username - {@link String}
     * @return user - {@link User}
     */
    @RequestMapping(
            value = {"/get/{username}"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getProfileByUsername(@PathVariable(name = "username") final String username) {
        User user = userDAO.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Could not locate a user with the username " + username));
        }

        return ResponseEntity.ok(user);
    }
}
