package com.example.library.services;

import com.example.library.controllers.requests.UpdateAuthorRequest;
import com.example.library.models.AuthorModel;
import com.example.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(@Autowired AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorModel readOne(long authorId){
        return authorRepository.findById(authorId).orElseThrow(() ->
                new IllegalArgumentException("No author with that ID!"));
    }

    public AuthorModel createAuthor(String authorName) {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setAuthorName(authorName);
        try {
            return authorRepository.save(authorModel);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    public long deleteAuthor(long authorId) {
        try {
            authorRepository.deleteById(authorId);
            return authorId;
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }


    public List<AuthorModel> readAll() {
        try {
            return (List<AuthorModel>) authorRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    public AuthorModel updateAuthor(UpdateAuthorRequest updateAuthorRequest){
        AuthorModel authorModel = readOne(updateAuthorRequest.getId());
        authorModel.setAuthorName(updateAuthorRequest.getAuthorName());
        try {
            return authorRepository.save(authorModel);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    public List<AuthorModel> search(String searchTerm){
        return authorRepository.findByAuthorNameContainingIgnoreCase(searchTerm);
    }
}
