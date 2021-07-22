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

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get registering users password
     * @return password - {@link String}
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
