package com.example.library.controllers.responses;

import com.example.library.models.AuthorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorView {
    private long authorId;
    private String authorName;
    private Set<ViewBookView> bagOfBooks;

    public AuthorView(AuthorModel authorModel) {
        this.authorId = authorModel.getId();
        this.authorName = authorModel.getAuthorName();
        this.bagOfBooks = authorModel.getBagOfBooks().stream().map(x -> new ViewBookView(x.getId(), x.getBookTitle(),
                x.getCopiesAvailable())).collect(Collectors.toSet());
    }
}
