package com.example.library.controllers.responses;

import com.example.library.models.AdminModel;
import com.example.library.models.MemberModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminView {
    private long adminId;
    private String adminUsername;

    public AdminView(AdminModel adminModel) {
        this.adminId=adminModel.getId();
        this.adminUsername=adminModel.getAdminUsername();
    }
}