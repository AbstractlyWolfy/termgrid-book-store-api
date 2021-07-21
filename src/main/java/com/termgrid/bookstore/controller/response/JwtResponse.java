package com.termgrid.bookstore.controller.response;

import java.util.List;

public class JwtResponse {

    private final int id;
    private final String username;
    private final String token;

    public JwtResponse(int id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
