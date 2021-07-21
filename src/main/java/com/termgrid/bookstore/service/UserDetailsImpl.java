package com.termgrid.bookstore.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.termgrid.bookstore.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    /** The user details id */
    private final int id;

    /** The username */
    private final String username;

    /** The password of the user, json ignored to stop it showing in json strings */
    @JsonIgnore
    private final String password;

    /** Appropriate authorities */
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(int id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Build an instance of {@link UserDetailsImpl} with appropriate authorities
     *
     * @param user - {@link User}
     *
     * @return UserDetailsImpl
     */
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Get the id
     *
     * @return id - {@link Integer}
     */
    public int getId() {
        return id;
    }

    /**
     * Get the username
     *
     * @return username - {@link String}
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Get the password
     *
     * @return password - {@link String}
     */
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
