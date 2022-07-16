package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRecordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private java.sql.Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @NonNull
    private BookModel book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @NonNull
    private MemberModel member;

    @Column(nullable = false)
    @NonNull
    private LoanOption loanOption;

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

}