package com.termgrid.bookstore.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email_address" }) })
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "email_address", unique = true)
    @NonNull
    private String email;

    /**
     * Get the id for the user
     *
     * @return id - {@link Integer}
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get the users first name
     *
     * @return first name - {@link String}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the users first name
     *
     * @param firstName - {@link String}
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the users last name
     *
     * @return last name - {@link String}
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the users last name
     *
     * @param lastName - {@link String}
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the users email address
     *
     * @return email - {@link String}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the users email
     *
     * @param email - {@link String}
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
