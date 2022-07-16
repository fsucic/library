package com.example.library.services;

import com.example.library.controllers.requests.BookRequest;
import com.example.library.models.AuthorModel;
import com.example.library.models.BookModel;
import com.example.library.repositories.AuthorRepository;
import com.example.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(@Autowired BookRepository bookRepository, @Autowired AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository=authorRepository;
    }


    public BookModel createBook(BookRequest bookRequest){ //exceptions
        AuthorModel author = authorRepository.findById(bookRequest.getAuthorId());
        if (author!=null){
            if (!author.hasBook(bookRequest.getBookTitle())){
                BookModel bookModel = new BookModel();
                bookModel.setBookTitle(bookRequest.getBookTitle());
                bookModel.setAuthor(author);
                bookModel.setCopiesAvailable(bookRequest.getCopiesAvailable());
                author.addBook(bookModel);
                return bookRepository.save(bookModel);
            }
            else {
                return null;
            }
        }
        else{
            return null;
        }

    }

    /*
    public long deleteBook(BookRequest bookRequest){ //what to return
        AuthorModel author = authorRepository.findByAuthorName(bookRequest.getAuthorName());
        return bookRepository.deleteByBookTitleAndAuthor(bookRequest.getBookTitle(), author);
    }


    public boolean deleteAll(){ //kako handleati try catch
        try {
            bookRepository.deleteAll();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<BookModel> readAll(){
        try {
            return (ArrayList<BookModel>) bookRepository.findAll();
        }
        catch (Exception e){
            return null;
        }
    }

    public BookModel updateBookCopiesAvailable(BookRequest bookRequest){
        AuthorModel author = authorRepository.findByAuthorName(bookRequest.getAuthorName());
        BookModel book = bookRepository.findByBookTitleAndAuthor(bookRequest.getBookTitle(), author);
        if (book != null){
            book.setCopiesAvailable(bookRequest.getCopiesAvailable());
        }
        return bookRepository.save(book);

    }
*/
}