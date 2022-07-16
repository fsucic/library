package com.example.library.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewBookView {
    private long bookId;
    private String bookTitle;
    private int copiesAvailable;
}