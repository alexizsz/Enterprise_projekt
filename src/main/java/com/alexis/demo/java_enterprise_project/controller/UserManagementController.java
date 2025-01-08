package com.alexis.demo.java_enterprise_project.controller;

import com.alexis.demo.java_enterprise_project.model.AppUser;
import com.alexis.demo.java_enterprise_project.repository.AppUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/manageuser")
public class UserManagementController {

    private final AppUserRepository appUserRepository;

    public UserManagementController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping
    public String currentUsersList(Model model){
        List<AppUser> usersList = appUserRepository.findAll();

        model.addAttribute("users", usersList);
        return "manageuser";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        appUserRepository.deleteById(id);
        model.addAttribute("message", "User deleted successfully");
        return "redirect:/manageuser";
    }
}
