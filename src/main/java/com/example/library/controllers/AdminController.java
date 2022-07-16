package com.example.library.controllers;

import com.example.library.controllers.requests.AdminRequest;
import com.example.library.controllers.responses.AdminView;
import com.example.library.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(@Autowired AdminService adminService) {
        this.adminService = adminService;
    }


    @Transactional
    @PostMapping
    public AdminView createAdmin(@RequestBody AdminRequest adminRequest) {
        return new AdminView(adminService.createAdmin(adminRequest));
    }

}
