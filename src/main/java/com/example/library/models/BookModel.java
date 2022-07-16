package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bookTitle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @NonNull
    private AuthorModel author;

    @Column(nullable = false)
    @NonNull
    private int copiesAvailable;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<LoanRecordModel> bagOfLoans = new HashSet<>();

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookModel )) return false;
        return id != null && id.equals(((BookModel) o).getId());
    }

    public void addLoan(LoanRecordModel loanRecordModel){
        bagOfLoans.add(loanRecordModel);
    }

    public void removeLoan(LoanRecordModel loanRecordModel){
        bagOfLoans.remove(loanRecordModel);
    }

}