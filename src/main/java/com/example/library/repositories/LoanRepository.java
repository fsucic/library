package com.example.library.repositories;

import com.example.library.models.BookModel;
import com.example.library.models.LoanModel;
import com.example.library.models.MemberModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<LoanModel, Long> {
    long countByBookAndMember(BookModel book, MemberModel memberModel);
    List<LoanModel> findAllByMemberOrderByTimestampDesc(MemberModel memberModel);
}
