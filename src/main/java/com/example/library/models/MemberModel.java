package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String memberUsername;

    @Column(nullable = false)
    @NonNull
    private String memberPassword;

    @Column(nullable = false)
    @NonNull
    private int numberOfBooksLoaned;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<LoanRecordModel> bagOfLoans = new HashSet<>();

    public void addLoan(LoanRecordModel loanRecordModel){
        bagOfLoans.add(loanRecordModel);
    }

    public void removeLoan(LoanRecordModel loanRecordModel){
        bagOfLoans.remove(loanRecordModel);
    }

}