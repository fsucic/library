package com.example.library.controllers.responses;


import com.example.library.models.AuthorModel;
import com.example.library.models.BookModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchView {
    private long authorId;
    private long bookId;
    private String authorName;
    private String bookTitle;

    public SearchView(AuthorModel authorModel) {
        this.authorId = authorModel.getId();
        this.authorName = authorModel.getAuthorName();
    }

    public SearchView(BookModel bookModel){
        this.bookId = bookModel.getId();
        this.bookTitle = bookModel.getBookTitle();
    }
}
