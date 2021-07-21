package com.termgrid.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.termgrid.bookstore.model.role.Role;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "username", unique = true)
    @NonNull
    private String username;

    @Column(name = "password")
    @NonNull
    @JsonIgnore
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "profile_picture")
    private String picture;

    @Column(name = "date_created")
    private Date created;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "roles",
            joinColumns = @JoinColumn(referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get the id for the user
     *
     * @return id - {@link Integer}
     */
    @NonNull
    public Integer getId() {
        return id;
    }

    /**
     * Get the users first name
     *
     * @return first name - {@link String}
     */
    @NonNull
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
    @NonNull
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
     * Get the users username
     *
     * @return email - {@link String}
     */
    @NonNull
    public String getUsername() {
        return username;
    }

    /**
     * Set the users username
     *
     * @param username - {@link String}
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the users password
     *
     * @return password - {@link String}
     */
    @NonNull
    public String getPassword() {
        return password;
    }

    /**
     * Set the users password
     * @param password - {@link String}
     */
    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    /**
     * Get the date this user was created
     * @return created - {@link Date}
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Get the JWT token
     * @return token - {@link String}
     */
    public String getToken() {
        return token;
    }

    /**
     * Set this users JWT Token
     * @param token - token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Get the users roles
     * @return roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Set all new roles to a user
     * @param roles - {@link Set}
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
