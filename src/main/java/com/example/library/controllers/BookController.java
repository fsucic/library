package com.example.library.controllers;

import com.example.library.controllers.requests.BookRequest;
import com.example.library.controllers.requests.UpdateBookRequest;
import com.example.library.controllers.responses.BookView;
import com.example.library.models.BookModel;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    private final BookService bookService;

    public BookController(@Autowired BookService bookService) {
        this.bookService = bookService;
    }


    @Transactional
    @PostMapping
    public BookView createBook(@RequestBody BookRequest bookRequest) {
        BookModel bookModel = bookService.createBook(bookRequest);
        return new BookView(bookModel);
    }


    @Transactional
    @DeleteMapping("/{bookId}")
    public long deleteBook(@PathVariable String bookId) { //what to return?
        return bookService.deleteBook(Long.parseLong(bookId));
    }


    @Transactional
    @GetMapping
    public Set<BookView> readAll() {
        return bookService.readAll().stream().map(BookView::new).collect(Collectors.toSet());
    }

    @Transactional
    @GetMapping("/{bookId}")
    public BookView readOne(@PathVariable String bookId) {
        return new BookView(bookService.readOne(Long.parseLong(bookId)));
    }


    @Transactional
    @PutMapping
    public BookView updateBook(@RequestBody UpdateBookRequest updateBookRequest) {
        return new BookView(bookService.updateBook(updateBookRequest));
    }

    @Transactional
    @GetMapping("/out-of-stock")
    public List<BookView> outOfStock() {
        return bookService.outOfStock().stream().map(BookView::new).collect(Collectors.toList());
    }

}