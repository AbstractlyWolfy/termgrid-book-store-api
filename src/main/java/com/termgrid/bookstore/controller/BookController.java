package com.termgrid.bookstore.controller;

import com.termgrid.bookstore.controller.request.books.BookCreateRequest;
import com.termgrid.bookstore.controller.request.books.CommentCreateRequest;
import com.termgrid.bookstore.controller.request.books.ReviewCreateRequest;
import com.termgrid.bookstore.controller.response.GenericResponse;
import com.termgrid.bookstore.dao.BookDAO;
import com.termgrid.bookstore.dao.CommentDAO;
import com.termgrid.bookstore.dao.ReviewDAO;
import com.termgrid.bookstore.dao.UserDAO;
import com.termgrid.bookstore.model.Book;
import com.termgrid.bookstore.model.Comment;
import com.termgrid.bookstore.model.Review;
import com.termgrid.bookstore.model.User;
import com.termgrid.bookstore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Create a new book
     * @param request - {@link BookCreateRequest}
     * @return book
     */
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
        book.setCreated(new Date());
        bookDAO.save(book);

        return ResponseEntity.ok(book);
    }

    /**
     * Get a book by its id.
     * @param id - Book id - {@link Integer}
     * @return book - {@link Book}
     */
    @RequestMapping(
            value = {"/get/{id}"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getBookById(@PathVariable(name = "id") final String id) {
        Book book = bookDAO.findById(Integer.parseInt(id)).orElse(null);

        if (book == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Could not locate a book with the id " + id));
        }

        return ResponseEntity.ok(book);
    }

    /**
     * Get a review by its id.
     * @param id - Review id - {@link Integer}
     * @return review - {@link Review}
     */
    @RequestMapping(
            value = {"/review/get/{id}"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getReviewById(@PathVariable(name = "id") final String id) {
        Review review = reviewDAO.findById(Integer.parseInt(id)).orElse(null);

        if (review == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Could not locate a review with the id " + id));
        }

        return ResponseEntity.ok(review);
    }

    /**
     * Create a new review and associate it with a specific book.
     * @param request - {@link ReviewCreateRequest}
     * @param servletRequest - {@link HttpServletRequest}
     * @return Review - {@link Review}
     */
    @RequestMapping(
            value = {"/review/create"},
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createReview(@RequestBody ReviewCreateRequest request, HttpServletRequest servletRequest) {
        String username = jwtUtil.getUsernameFromJwtToken(jwtUtil.parseJwt(servletRequest));

        Book book = bookDAO.findById(request.getBookId()).orElse(null);
        User user = userDAO.findByUsername(username).orElse(null);

        if (book == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Could not locate a book with the id " + request.getBookId()));
        }

        if (user == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Could not locate your user"));
        }

        Review review = new Review();
        review.setBook(book);
        review.setDescription(request.getDescription());
        review.setRating(request.getRating());
        review.setAuthor(user);
        review.setCreated(new Date());

        reviewDAO.save(review);

        return ResponseEntity.ok(review);
    }

    /**
     * Get all reviews associated with a specific book.
     * @param id - Book Id - {@link Integer}
     * @param page - Current Page - {@link Integer}
     * @param perPage - Per Page - {@link Integer}
     * @return reviews - Review[]
     */
    @RequestMapping(
            value = {"/review/all/{id}"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getReviews(@PathVariable(name = "id") final String id, @RequestParam(name = "page", defaultValue = "0") final String page, @RequestParam(name = "perPage", defaultValue = "5") final String perPage) {
        Book book = bookDAO.findById(Integer.parseInt(id)).orElse(null);

        if (book == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Could not locate a book with the id " + id));
        }

        // Pagination
        int foundPage = Integer.parseInt(page);
        int foundPerPage = Integer.parseInt(perPage);

        Sort sorting = Sort.by("created").ascending();
        Pageable pageableReviews = PageRequest.of(foundPage, foundPerPage, sorting);

        Page<Review> reviews = reviewDAO.getAllByBook(book, pageableReviews);
        return ResponseEntity.ok(reviews.getContent());
    }

    /**
     * Get a specific comment by its id.
     * @param id - Comment Id - {@link Integer}
     * @return comment - {@link Comment}
     */
    @RequestMapping(
            value = {"/comment/get/{id}"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCommentById(@PathVariable(name = "id") final String id) {
        Comment comment = commentDAO.findById(Integer.parseInt(id)).orElse(null);

        if (comment == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Could not locate a comment with the id " + id));
        }

        return ResponseEntity.ok(comment);
    }

    /**
     * Get all comments associated with a specific review.
     * @param id - Review Id - {@link Integer}
     * @param page - Current Page - {@link Integer}
     * @param perPage - Per Page - {@link Integer}
     * @return comments - Comments[]
     */
    @RequestMapping(
            value = {"/comment/all/{id}"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getComments(@PathVariable(name = "id") final String id, @RequestParam(name = "page", defaultValue = "0") final String page, @RequestParam(name = "perPage", defaultValue = "5") final String perPage) {
        Review review = reviewDAO.findById(Integer.parseInt(id)).orElse(null);

        if (review == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Could not locate a review with the id " + id));
        }

        // Pagination
        int foundPage = Integer.parseInt(page);
        int foundPerPage = Integer.parseInt(perPage);

        Sort sorting = Sort.by("created").ascending();
        Pageable pageableReviews = PageRequest.of(foundPage, foundPerPage, sorting);

        Page<Comment> comments = commentDAO.getAllByReview(review, pageableReviews);
        return ResponseEntity.ok(comments.getContent());
    }

    /**
     * Create a new comment and associate it with a specific book review.
     * @param request - {@link CommentCreateRequest}
     * @param servletRequest - {@link HttpServletRequest}
     * @return Comment - {@link Comment}
     */
    @RequestMapping(
            value = {"/comment/create"},
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createComment(@RequestBody CommentCreateRequest request, HttpServletRequest servletRequest) {
        String username = jwtUtil.getUsernameFromJwtToken(jwtUtil.parseJwt(servletRequest));

        Review review = reviewDAO.findById(request.getReviewId()).orElse(null);
        User user = userDAO.findByUsername(username).orElse(null);

        if (review == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Could not locate a review with the id " + request.getReviewId()));
        }

        if (user == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Could not locate your user"));
        }

        Comment comment = new Comment();
        comment.setReview(review);
        comment.setComment(request.getComment());
        comment.setAuthor(user);
        comment.setCreated(new Date());
        commentDAO.save(comment);

        return ResponseEntity.ok(comment);
    }

    /**
     * Get all books
     * @return books
     */
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
