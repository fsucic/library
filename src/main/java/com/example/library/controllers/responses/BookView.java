package com.example.library.controllers.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookView {
    private long bookId;
    private String bookTitle;
    private ViewAuthorView author;
    private int copiesAvailable;
}
