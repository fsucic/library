package com.example.library.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {
    @NonNull
    private long id;
    @NonNull
    private String bookTitle;
    @NonNull
    private long authorId;
    @NonNull
    private int copiesAvailable;
}
