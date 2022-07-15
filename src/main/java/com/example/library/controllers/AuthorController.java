package com.example.library.controllers;

import com.example.library.controllers.requests.AuthorRequest;
import com.example.library.controllers.responses.AuthorView;
import com.example.library.models.AuthorModel;
import com.example.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(@Autowired AuthorService authorService) {
        this.authorService = authorService;
    }

    @Transactional
    @PostMapping
    public AuthorView createAuthor(@RequestBody AuthorRequest authorRequest){
        AuthorModel authorModel= authorService.createAuthor(authorRequest.getAuthorName());
        return new AuthorView(authorModel.getAuthorName());
    }

    @Transactional
    @DeleteMapping
    public long deleteAuthor(@RequestBody AuthorRequest authorRequest){ //what to return?
        return authorService.deleteAuthor(authorRequest.getAuthorName());
    }

    @Transactional
    @GetMapping
    public List<AuthorView> readAll(){
        return authorService.readAll().stream().map(x->new AuthorView(x.getAuthorName())).collect(Collectors.toList());
    }

}
