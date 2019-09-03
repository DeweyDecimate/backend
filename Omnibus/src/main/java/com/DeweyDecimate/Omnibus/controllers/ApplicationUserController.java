package com.DeweyDecimate.Omnibus.controllers;

import com.DeweyDecimate.Omnibus.models.ApplicationUser;
import com.DeweyDecimate.Omnibus.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/users")
    public RedirectView createUser(String username, String password, String firstname, String lastname, String userImg){
        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), firstname, lastname, userImg);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        applicationUserRepository.save(newUser);
        return new RedirectView("/myprofile");
    }

    @GetMapping("/myprofile")
    public String getMyprofile(Principal p, Model m){
        m.addAttribute("loggedUser", applicationUserRepository.findByUsername(p.getName()));
        return "myprofile";
    }
}
