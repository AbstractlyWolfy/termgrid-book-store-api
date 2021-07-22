package com.termgrid.bookstore.controller;

import com.termgrid.bookstore.controller.request.LoginRequest;
import com.termgrid.bookstore.controller.request.RegisterRequest;
import com.termgrid.bookstore.controller.response.GenericResponse;
import com.termgrid.bookstore.controller.response.JwtResponse;
import com.termgrid.bookstore.dao.RoleDAO;
import com.termgrid.bookstore.dao.UserDAO;
import com.termgrid.bookstore.model.User;
import com.termgrid.bookstore.model.role.Role;
import com.termgrid.bookstore.model.role.SiteRole;
import com.termgrid.bookstore.service.UserDetailsImpl;
import com.termgrid.bookstore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(path = "/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        User user = userDAO.findByUsername(request.getUsername()).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("There was an error logging you in."));
        }

        user.setToken(token);
        userDAO.save(user);

        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                token
        ));
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userDAO.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new GenericResponse("Username is already taken"));
        }

        User user = new User(request.getUsername(), encoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setCreated(new Date());

        Set<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(roleDAO.findByName(SiteRole.USER.name()));
        user.setRoles(defaultRoles);

        userDAO.save(user);

        return ResponseEntity.ok(new GenericResponse("Registered successfully"));
    }
}
