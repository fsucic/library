package com.example.library.controllers;

import com.example.library.controllers.requests.AuthorRequest;
import com.example.library.controllers.requests.BookRequest;
import com.example.library.controllers.responses.AuthorView;
import com.example.library.controllers.responses.BookView;
import com.example.library.models.AuthorModel;
import com.example.library.models.BookModel;
import com.example.library.services.AuthorService;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
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
    public BookView createBook(@RequestBody BookRequest bookRequest){
        BookModel bookModel= bookService.createBook(bookRequest);
        return new BookView(bookModel.getBookTitle(), new AuthorView(bookModel.getAuthor().getAuthorName()),
                bookModel.getCopiesAvailable());
    }

    @Transactional
    @DeleteMapping
    public long deleteBook(@RequestBody BookRequest bookRequest){ //what to return?
        return bookService.deleteBook(bookRequest);
    }

    @Transactional
    @DeleteMapping("/delete-all") //dal se ovak piše sa -
    public boolean deleteAll(){ //što vratiti
        return bookService.deleteAll();
    }


    @Transactional
    @GetMapping
    public List<BookView> readAll(){
        return bookService.readAll().stream().map(x->new BookView(x.getBookTitle(),
                new AuthorView(x.getAuthor().getAuthorName()),
                x.getCopiesAvailable())).collect(Collectors.toList());
    }

    @Transactional
    @PutMapping
    public BookView updateBookCopiesAvailable(@RequestBody BookRequest bookRequest){
        BookModel bookModel= bookService.updateBookCopiesAvailable(bookRequest);
        return new BookView(bookModel.getBookTitle(), new AuthorView(bookModel.getAuthor().getAuthorName()),
                bookModel.getCopiesAvailable());
    }


}