package com.example.library.controllers;

import com.example.library.controllers.responses.SearchView;
import com.example.library.models.AuthorModel;
import com.example.library.models.BookModel;
import com.example.library.services.AuthorService;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    private final AuthorService authorService;
    private final BookService bookService;

    public SearchController(@Autowired AuthorService authorService, @Autowired BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Transactional
    @GetMapping("/{searchTerm}")
    public List<SearchView> search(@PathVariable String searchTerm) {
        List<AuthorModel> authors = authorService.search(searchTerm);
        List<SearchView> searchResult = authors.stream().map(SearchView::new).collect(Collectors.toList());
        List<BookModel> books = bookService.search(searchTerm);
        for (BookModel book : books) {
            searchResult.add(new SearchView(book));
        }
        return searchResult;
    }
}
