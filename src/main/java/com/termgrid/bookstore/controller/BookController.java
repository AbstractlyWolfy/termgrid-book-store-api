package com.termgrid.bookstore.controller;

import com.termgrid.bookstore.controller.request.books.BookCreateRequest;
import com.termgrid.bookstore.controller.response.GenericResponse;
import com.termgrid.bookstore.controller.response.JwtResponse;
import com.termgrid.bookstore.dao.BookDAO;
import com.termgrid.bookstore.dao.CommentDAO;
import com.termgrid.bookstore.dao.ReviewDAO;
import com.termgrid.bookstore.dao.UserDAO;
import com.termgrid.bookstore.model.Book;
import com.termgrid.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/v1/book")
public final class BookController {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private CommentDAO commentDAO;

    @RequestMapping(
        value = {"/create"},
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> create(@RequestBody BookCreateRequest request) {

        User author = userDAO.findByUsername(request.getAuthor()).orElse(null);

        if (author == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Failed to create your book, the author did not exist."));
        }

        // Turn base64 to byte[]
        byte[] image = Base64.getEncoder().encode(request.getImage().getBytes(StandardCharsets.UTF_8));

        // Form slug
        String slug = request.getName().toLowerCase().replace(" ", "-");

        Book book = new Book();
        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setSlug(slug);
        book.setAuthor(author);
        book.setImage(image);
        bookDAO.save(book);

        return ResponseEntity.ok(new GenericResponse("Your book has been created successfully"));
    }

    @RequestMapping(
            value = {"/get/{id}"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getById(@PathVariable(name = "id") final String id) {
        Book book = bookDAO.getById(Integer.parseInt(id));
        return ResponseEntity.ok(book);
    }

    @RequestMapping(
        value = {"/all"},
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Book> getAll() {
        return bookDAO.findAll();
    }
}
