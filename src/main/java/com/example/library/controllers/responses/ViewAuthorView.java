package com.example.library.controllers.responses;

import com.example.library.models.AuthorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewAuthorView {
    private long authorId;
    private String authorName;

    public ViewAuthorView(AuthorModel authorModel){
        this.authorId = authorModel.getId();
        this.authorName = authorModel.getAuthorName();
    }
}