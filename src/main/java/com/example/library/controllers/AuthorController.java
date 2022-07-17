package com.example.library.controllers;

import com.example.library.controllers.requests.AuthorRequest;
import com.example.library.controllers.requests.UpdateAuthorRequest;
import com.example.library.controllers.responses.AuthorView;
import com.example.library.models.AuthorModel;
import com.example.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;
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
    public AuthorView createAuthor(@RequestBody AuthorRequest authorRequest) {
        AuthorModel authorModel = authorService.createAuthor(authorRequest.getAuthorName());
        return new AuthorView(authorModel);
    }

    @Transactional
    @DeleteMapping("/{authorId}")
    public long deleteAuthor(@PathVariable String authorId) { //what to return?
        return authorService.deleteAuthor(Long.parseLong(authorId));
    }

    @Transactional
    @GetMapping
    public Set<AuthorView> readAll() {
        return authorService.readAll().stream().map(AuthorView::new).collect(Collectors.toSet());
    }

    @Transactional
    @GetMapping("/{authorId}")
    public AuthorView readOne(@PathVariable String authorId) {
        return new AuthorView(authorService.readOne(Long.parseLong(authorId)));
    }

    @Transactional
    @PutMapping
    public AuthorView updateAuthor(@RequestBody UpdateAuthorRequest updateAuthorRequest) {
        return new AuthorView(authorService.updateAuthor(updateAuthorRequest));
    }
}
