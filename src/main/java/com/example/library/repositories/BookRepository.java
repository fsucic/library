package com.example.library.repositories;

import com.example.library.models.AuthorModel;
import com.example.library.models.BookModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookModel, Long> {
    BookModel findById(long id);
}