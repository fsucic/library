package com.example.library.controllers.responses;

import com.example.library.models.LoanModel;
import com.example.library.models.LoanOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanView {
    private long memberId;
    private long bookId;
    private LoanOption loanOption;
    private java.sql.Timestamp timestamp;
    private String bookTitle;
    private String memberUsername;
    private long loanId;

    public LoanView(LoanModel loanModel) {
        this.memberId = loanModel.getMember().getId();
        this.bookId = loanModel.getBook().getId();
        this.loanOption = loanModel.getLoanOption();
        this.timestamp = loanModel.getTimestamp();
        this.bookTitle = loanModel.getBook().getBookTitle();
        this.memberUsername = loanModel.getMember().getMemberUsername();
        this.loanId = loanModel.getId();
    }
}
