package com.example.library.models;

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

    @ManyToOne(optional = false)
    @NonNull
    private BookModel book;

    @ManyToOne(optional = false)
    @NonNull
    private MemberModel member;

    @Column(nullable = false)
    @NonNull
    private LoanOption loanOption;


}