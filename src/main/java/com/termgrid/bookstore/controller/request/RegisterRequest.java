package com.termgrid.bookstore.controller.request;

public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    /**
     * Get registering users first name
     * @return first name - {@link String}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get registering users last name
     * @return last name - {@link String}
     */
    public String getLastName() {
        return lastName;
    }

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
