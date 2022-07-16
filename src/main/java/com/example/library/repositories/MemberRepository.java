package com.example.library.repositories;

import com.example.library.models.MemberModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<MemberModel, Long> {
}