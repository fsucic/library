package com.example.library.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    @NonNull
    private String memberUsername;
    @NonNull
    private String memberPassword;
}