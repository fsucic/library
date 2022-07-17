package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String authorName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<BookModel> bagOfBooks = new HashSet<>();

    public void addBook(BookModel bookModel) {
        bagOfBooks.add(bookModel);
    }

    public void removeBook(BookModel bookModel) {
        bagOfBooks.remove(bookModel);
    }

    public boolean hasBook(String bookName) {
        for (BookModel book : bagOfBooks) {
            if (book.getBookTitle().equals(bookName)) {
                return true;
            }
        }
        return false;
    }

}