package com.example.library.repositories;

import com.example.library.models.AuthorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorModel, Long> {
    long deleteById(long id);
    AuthorModel findById(long id);
}
