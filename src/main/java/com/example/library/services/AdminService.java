package com.example.library.services;

import com.example.library.controllers.requests.AdminRequest;
import com.example.library.models.AdminModel;
import com.example.library.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(@Autowired AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminModel createAdmin(AdminRequest adminRequest) {
        AdminModel adminModel = new AdminModel();
        adminModel.setAdminUsername(adminRequest.getAdminUsername());
        adminModel.setAdminPassword(adminRequest.getAdminPassword());
        try {
            return adminRepository.save(adminModel);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

}