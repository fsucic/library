package com.example.library.controllers.requests;

import com.example.library.models.LoanOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLoanRequest {
    @NonNull
    private long id;
    @NonNull
    private long bookId;
    @NonNull
    private long memberId;
    @NonNull
    private LoanOption loanOption;
}