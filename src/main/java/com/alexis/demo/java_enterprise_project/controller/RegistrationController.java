package com.alexis.demo.java_enterprise_project.controller;

import com.alexis.demo.java_enterprise_project.model.AppUser;
import com.alexis.demo.java_enterprise_project.repository.AppUserRepository;
import com.alexis.demo.java_enterprise_project.utility.HtmlUtil;
import com.alexis.demo.java_enterprise_project.utility.MaskingUtils;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/registerpage")
public class RegistrationController {

    private final static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final Map<String, String> userEmails = new HashMap<>();
    private final HtmlUtil htmlUtil;
    private final AppUserRepository appUserRepository;

    @Autowired
    public RegistrationController(HtmlUtil htmlUtil, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, MaskingUtils maskingUtils, AppUserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.htmlUtil = htmlUtil;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping
    public String registrationForm(Model model){
        logger.debug("Access of registration form");
        model.addAttribute("user", new AppUser());
        return "registerpage";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user")AppUser appUser, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("error", "There are errors in the form, please correct them");
            logger.debug("Form has validation errors for attempted registration of user: {}", appUser.getName());
            System.out.println(appUser);
            return "registerpage";
        }
        if (appUserRepository.findByName(appUser.getName()) != null) {
            model.addAttribute("error", "A user with this name already exists. Please choose a different name.");
            logger.debug("Attempted registration with duplicate username: {}", appUser.getName());
            return "registerpage";
        }
        if (appUserRepository.findByEmail(appUser.getEmail()) != null) {
            model.addAttribute("error", "A user with this email already exists. Please use a different email.");
            logger.debug("Attempted registration with duplicate email: {}", appUser.getEmail());
            return "registerpage";
        }

        String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        UserDetails newUser = User.withUsername(appUser.getName())
                .password(appUser.getPassword())
                .roles("USER")
                .build();
        ((InMemoryUserDetailsManager) userDetailsService).createUser(newUser);
    
        userEmails.put(appUser.getName(), htmlUtil.escapeHtml(appUser.getEmail()));
        System.out.println("Created user :" + appUser.getEmail());
        model.addAttribute("user", appUser);
        model.addAttribute("userName", appUser.getName());

        return "successregisterpage";

    }

}
