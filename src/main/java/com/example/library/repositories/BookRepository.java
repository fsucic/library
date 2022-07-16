package com.example.library.repositories;

import com.example.library.models.AuthorModel;
import com.example.library.models.BookModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookModel, Long> {
    List<BookModel> findAllByCopiesAvailableEquals(int d);
    List<BookModel> findByBookTitleContainingIgnoreCase(String searchTerm);
}