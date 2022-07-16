package com.example.library.services;

import com.example.library.controllers.requests.UpdateAuthorRequest;
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

    public long deleteAuthor(long authorId) {
        try {
            return authorRepository.deleteById(authorId);
        } catch (Exception e) {
            throw e;
        }
    }


    public List<AuthorModel> readAll() {
        try {
            return (ArrayList<AuthorModel>) authorRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public AuthorModel readOne(long authorId){
        AuthorModel authorModel;
        try {
            authorModel = authorRepository.findById(authorId);
        } catch (Exception e) {
            throw e;
        }
        if (authorModel == null){
            //throw exception
            return null;
        }
        else
        {
            return authorModel;
        }
    }

    public AuthorModel updateAuthor(UpdateAuthorRequest updateAuthorRequest){
        AuthorModel authorModel;
        try {
            authorModel = authorRepository.findById(updateAuthorRequest.getId());
        } catch (Exception e) {
            throw e;
        }
        if (authorModel == null){
            //throw exception
            return null;
        }
        else
        {
            authorModel.setAuthorName(updateAuthorRequest.getAuthorName());
            return authorModel;
        }
    }
}
