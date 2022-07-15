package com.example.library.services;

import com.example.library.models.AuthorModel;
import com.example.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(@Autowired AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorModel createAuthor(String authorName){
        AuthorModel authorModel = new AuthorModel();
        authorModel.setAuthorName(authorName);
        try {
            return authorRepository.save(authorModel);
        }
        catch (Exception e){
            return null;
        }
    }

    public long deleteAuthor(String authorName){
        try {
            return authorRepository.deleteByAuthorName(authorName);
        }
        catch (Exception e){
            return -1;
        }
    }

    public boolean deleteAll(){ //kako handleati try catch
        try {
            authorRepository.deleteAll();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<AuthorModel> readAll(){
        try {
            return (ArrayList<AuthorModel>) authorRepository.findAll();
        }
        catch (Exception e){
            return null;
        }
    }
}
