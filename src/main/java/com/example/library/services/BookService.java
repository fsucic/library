package com.example.library.services;

import com.example.library.controllers.requests.BookRequest;
import com.example.library.controllers.requests.UpdateBookRequest;
import com.example.library.models.AuthorModel;
import com.example.library.models.BookModel;
import com.example.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(@Autowired BookRepository bookRepository, @Autowired AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public BookModel readOne(long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("No book with that ID"));
    }

    public BookModel createBook(BookRequest bookRequest) { //exceptions
        AuthorModel author = authorService.readOne(bookRequest.getAuthorId());

        if (bookRequest.getCopiesAvailable() < 0) {
            throw new IllegalArgumentException("Cannot create books with less than 0 copies available!");
        }
        if (author.hasBook(bookRequest.getBookTitle())) {
            throw new IllegalArgumentException("Author already has a book with the same name!");
        }

        BookModel bookModel = new BookModel();
        bookModel.setBookTitle(bookRequest.getBookTitle());
        bookModel.setAuthor(author);
        bookModel.setCopiesAvailable(bookRequest.getCopiesAvailable());
        author.addBook(bookModel);
        try {
            return bookRepository.save(bookModel);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    public long deleteBook(long bookId) { //what to return
        BookModel book = readOne(bookId);

        AuthorModel authorModel = book.getAuthor();
        authorModel.removeBook(book);
        try {
            bookRepository.deleteById(bookId);
        } catch (Exception e) {
            //exception
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
        return bookId;
    }

    public List<BookModel> readAll() {
        try {
            return (List<BookModel>) bookRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    public BookModel updateBook(UpdateBookRequest updateBookRequest) {
        BookModel bookModel = readOne(updateBookRequest.getId());

        AuthorModel oldAuthor = bookModel.getAuthor();
        AuthorModel author = authorService.readOne(updateBookRequest.getAuthorId());

        if (author.hasBook(updateBookRequest.getBookTitle()) && !oldAuthor.equals(author)) {
            throw new IllegalArgumentException("Author already has a book with the same name!");
        }

        oldAuthor.removeBook(bookModel);
        bookModel.setBookTitle(updateBookRequest.getBookTitle());
        bookModel.setAuthor(author);
        bookModel.setCopiesAvailable(updateBookRequest.getCopiesAvailable());
        author.addBook(bookModel);
        try {
            return bookRepository.save(bookModel);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    public List<BookModel> outOfStock() {
        try {
            return bookRepository.findAllByCopiesAvailableEquals(0);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    public List<BookModel> search(String searchTerm) {
        try {
            return bookRepository.findByBookTitleContainingIgnoreCase(searchTerm);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

}