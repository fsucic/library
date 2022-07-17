package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<LoanModel> bagOfLoans = new HashSet<>();

    public void addLoan(LoanModel loanModel) {
        bagOfLoans.add(loanModel);
    }

    public void removeLoan(LoanModel loanModel) {
        bagOfLoans.remove(loanModel);
    }

}