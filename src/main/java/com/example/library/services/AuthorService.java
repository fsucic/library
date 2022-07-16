package com.example.library.services;

import com.example.library.models.AuthorModel;
import com.example.library.models.BookModel;
import com.example.library.repositories.AuthorRepository;
import com.example.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(@Autowired AuthorRepository authorRepository, @Autowired BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public AuthorModel createAuthor(String authorName) {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setAuthorName(authorName);
        try {
            return authorRepository.save(authorModel);
        } catch (Exception e) {
            throw e;
        }
    }

    public long deleteAuthor(String authorName) { //
        return authorRepository.deleteByAuthorName(authorName);
    }


    public List<AuthorModel> readAll() {
        try {
            return (ArrayList<AuthorModel>) authorRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }
}
