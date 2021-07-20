package com.termgrid.bookstore.controller;

import com.termgrid.bookstore.dao.UserDAO;
import com.termgrid.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/v1/user")
public final class UserController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(
            value = {"/create"},
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {

        if (!payload.containsKey("firstName") || !payload.containsKey("lastName") || !payload.containsKey("email")) {
            Map<String, String> body = new HashMap<>();
            body.put("message", "You have not passed the necessary arguments");
            return ResponseEntity.ok(body);
        }

        String firstName = (String) payload.get("firstName");
        String lastName = (String) payload.get("lastName");
        String email = (String) payload.get("email");

        if (userDAO.findByEmail(email) != null) {
            Map<String, String> body = new HashMap<>();
            body.put("message", "A user with this email address already exists");
            return ResponseEntity.ok(body);
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userDAO.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping(path = "/all")
    public Iterable<User> getAll() {
        return userDAO.findAll();
    }
}
