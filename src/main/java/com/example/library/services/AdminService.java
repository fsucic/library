package com.example.library.services;

import com.example.library.controllers.requests.AdminRequest;
import com.example.library.models.AdminModel;
import com.example.library.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminService(@Autowired AdminRepository adminRepository, @Autowired BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public AdminModel createAdmin(AdminRequest adminRequest) {
        AdminModel adminModel = new AdminModel();
        adminModel.setAdminUsername(adminRequest.getAdminUsername());
        adminModel.setAdminPassword(bCryptPasswordEncoder.encode(adminRequest.getAdminPassword()));
        try {
            return adminRepository.save(adminModel);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

}