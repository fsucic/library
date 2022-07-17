package com.example.library.services;

import com.example.library.models.AuthorModel;
import com.example.library.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.thenObject;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void shouldCreateAuthor() {
        //given
        var authorName = "Kafka";
        var expectedAuthor = new AuthorModel(1L, authorName, new HashSet<>());
        given(authorRepository.save(any())).willReturn(expectedAuthor);

        //when
        var actualAuthor = authorService.createAuthor(authorName);

        //then
        thenObject(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    void shouldReadOne() {
        //given
        var authorId = 3L;
        var expectedAuthor = new AuthorModel(authorId, "Kafka", new HashSet<>());
        given(authorRepository.findById(authorId)).willReturn(Optional.of(expectedAuthor));

        //when
        var actualAuthor = authorService.readOne(authorId);

        //then
        thenObject(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    void shouldNotReadOne() {
        //given
        var authorId = 3L;
        given(authorRepository.findById(authorId)).willReturn(Optional.empty());

        //then
        assertThrows(IllegalArgumentException.class, () -> authorService.readOne(authorId));

    }

}
