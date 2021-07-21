package com.termgrid.bookstore.controller;

import com.termgrid.bookstore.controller.request.books.BookCreateRequest;
import com.termgrid.bookstore.dao.BookDAO;
import com.termgrid.bookstore.dao.CommentDAO;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/v1/book")
public final class BookController {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CommentDAO commentDAO;

    @RequestMapping(
        value = {"/create"},
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> create(@RequestBody BookCreateRequest request) {
        return null;
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
