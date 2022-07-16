package com.example.library.controllers.responses;

import com.example.library.models.MemberModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberView {
    private long memberId;
    private String memberUsername;
    private int numberOfBooksLoaned;

    public MemberView(MemberModel memberModel) {
        this.memberId = memberModel.getId();
        this.memberUsername = memberModel.getMemberUsername();
        this.numberOfBooksLoaned = memberModel.getNumberOfBooksLoaned();
    }
}
