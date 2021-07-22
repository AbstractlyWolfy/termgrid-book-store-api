package com.termgrid.bookstore.controller.response;

import com.termgrid.bookstore.model.User;

import java.util.List;

public class JwtResponse {

    private final User user;
    private final String token;

    public JwtResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
