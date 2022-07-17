package com.example.library.services;

import com.example.library.controllers.requests.BookRequest;
import com.example.library.models.AuthorModel;
import com.example.library.models.BookModel;
import com.example.library.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.thenObject;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {


    private BookRequest bookRequest;
    private AuthorModel author;


    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorService authorService;
    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRequest = new BookRequest("Amerika", 1L, 8);
        author = new AuthorModel(1L, "Kafka", new HashSet<>());
    }

    @Test
    void shouldCreateBook() {
        //given
        var expectedBook = new BookModel(1L, "Amerika", author, 8, new HashSet<>());

        given(authorService.readOne(bookRequest.getAuthorId())).willReturn(author);
        given(bookRepository.save(any())).willReturn(expectedBook);

        //when
        var actualBook = bookService.createBook(bookRequest);

        //then
        thenObject(actualBook).isEqualTo(expectedBook);
        assertThat(author.hasBook("Amerika")).isEqualTo(true);

    }

    @Test
    void shouldNotCreateBookBecauseAuthorHasIt() {
        //given
        author.addBook(new BookModel(1L, "Amerika", author, 8, new HashSet<>()));

        given(authorService.readOne(bookRequest.getAuthorId())).willReturn(author);

        //then
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(bookRequest));
    }


}
