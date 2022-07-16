package com.example.library.controllers.responses;


import com.example.library.models.BookModel;
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

    public BookView(BookModel bookModel){
        this.bookId = bookModel.getId();
        this.bookTitle = bookModel.getBookTitle();
        this.copiesAvailable=bookModel.getCopiesAvailable();
        this.author = new ViewAuthorView(bookModel.getAuthor());
    }
}
