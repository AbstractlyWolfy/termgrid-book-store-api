package com.termgrid.bookstore.controller.request;

public class LoginRequest {

    private String username;
    private String password;

    /**
     * Get registering users username
     * @return username - {@link String}
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get registering users password
     * @return password - {@link String}
     */
    public String getPassword() {
        return password;
    }
}
