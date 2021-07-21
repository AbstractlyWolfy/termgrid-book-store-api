package com.termgrid.bookstore.controller.response;

import java.util.List;

public class JwtResponse {

    private final int id;
    private final String username;
    private final List<String> roles;
    private final String token;

    public JwtResponse(int id, String username, List<String> roles, String token) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }
}
