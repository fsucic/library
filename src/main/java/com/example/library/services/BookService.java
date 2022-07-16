package com.example.library.services;

import com.example.library.controllers.requests.BookRequest;
import com.example.library.controllers.requests.UpdateBookRequest;
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
        AuthorModel author;
        try {
            author = authorRepository.findById(bookRequest.getAuthorId());
        } catch (Exception e) {
            throw e;
        }
        if (author!=null){
            if (!author.hasBook(bookRequest.getBookTitle())){
                BookModel bookModel = new BookModel();
                bookModel.setBookTitle(bookRequest.getBookTitle());
                bookModel.setAuthor(author);
                bookModel.setCopiesAvailable(bookRequest.getCopiesAvailable());
                author.addBook(bookModel);
                try{
                    return bookRepository.save(bookModel);
                } catch (Exception e) {
                    throw e;
                }
            }
            else {
                // author already has book with that title
                return null;
            }
        }
        else{
            //throw exe - no author
            return null;
        }
    }


    public long deleteBook(long bookId){ //what to return
        BookModel book;
        try {
            book = bookRepository.findById(bookId);
        } catch (Exception e){
            //exception
            throw e;
        }
        if(book==null){
            return bookId;
        }
        AuthorModel authorModel = book.getAuthor();
        authorModel.removeBook(book);
        try {
            bookRepository.deleteById(bookId);
        } catch (Exception e){
            //exception
            throw e;
        }
        return bookId;
    }


    public List<BookModel> readAll(){
        try {
            return (ArrayList<BookModel>) bookRepository.findAll();
        }
        catch (Exception e){
            return null;
        }
    }

    public BookModel readOne(long bookId){
        BookModel bookModel;
        try {
            bookModel = bookRepository.findById(bookId);
        } catch (Exception e) {
            throw e;
        }
        if (bookModel == null){
            //throw exception
            return null;
        }
        else
        {
            return bookModel;
        }
    }


    public BookModel updateBook(UpdateBookRequest updateBookRequest){
        BookModel bookModel;
        try {
            bookModel = bookRepository.findById(updateBookRequest.getId());
        } catch (Exception e) {
            throw e;
        }

        if (bookModel == null){
            //throw exception
            return null;
        }
        AuthorModel oldAuthor = bookModel.getAuthor();
        AuthorModel author;

        try {
            author = authorRepository.findById(updateBookRequest.getAuthorId());
        } catch (Exception e) {
            throw e;
        }
        if (author!=null){
            if (!author.hasBook(updateBookRequest.getBookTitle())){
                oldAuthor.removeBook(bookModel);
                bookModel.setBookTitle(updateBookRequest.getBookTitle());
                bookModel.setAuthor(author);
                bookModel.setCopiesAvailable(updateBookRequest.getCopiesAvailable());
                author.addBook(bookModel);
                return bookModel;
            }
            else {
                // author already has book with that title
                return null;
            }
        }
        else{
            //throw exe - no author
            return null;
        }
    }

}