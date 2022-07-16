package com.example.library.controllers.responses;


import com.example.library.models.AuthorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookView {
    private long bookId;
    private String bookTitle;
    private AuthorView author;
    private int copiesAvailable;
}
