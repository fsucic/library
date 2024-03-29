package com.example.library.repositories;

import com.example.library.models.AuthorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorModel, Long> {
    List<AuthorModel> findByAuthorNameContainingIgnoreCase(String searchTerm);
}
