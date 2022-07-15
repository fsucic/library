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
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String bookTitle;

    @ManyToOne(optional = false)
    @NonNull
    private AuthorModel author;

    @Column(nullable = false)
    @NonNull
    private int copiesAvailable;


}