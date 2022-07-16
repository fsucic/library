package com.example.library.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberView {
    private String memberUsername;
    private int numberOfBooksLoaned;
}
