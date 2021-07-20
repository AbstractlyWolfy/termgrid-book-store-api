package com.termgrid.bookstore.controller;

import com.termgrid.bookstore.dao.BookDAO;
import com.termgrid.bookstore.dao.UserDAO;
import com.termgrid.bookstore.model.Book;
import com.termgrid.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/v1/book")
public final class BookController {

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping(
        value = {"/create"},
        method = RequestMethod.POST,
        produces = "application/json",
        consumes = "application/json"
    )
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        return null;
    }

    @RequestMapping(
        value = {"/all"},
        method = RequestMethod.GET,
        produces = "application/json",
        consumes = "application/json"
    )
    public Iterable<Book> getAll() {
        return bookDAO.findAll();
    }
}
