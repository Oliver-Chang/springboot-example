package com.oliver.ElectronicCommerce.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminRepository repositoryl;

    @GetMapping("/admin")
    public String getTest() {
        return "admin";
    }
}
