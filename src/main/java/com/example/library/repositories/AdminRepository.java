package com.example.library.repositories;

import com.example.library.models.AdminModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<AdminModel, Long> {
}
