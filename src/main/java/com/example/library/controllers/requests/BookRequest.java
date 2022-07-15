package com.example.library.controllers.requests;

import com.example.library.controllers.responses.AuthorView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String bookTitle;
    private String authorName;
    private int copiesAvailable;

}