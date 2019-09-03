package com.DeweyDecimate.Omnibus.controllers;

import com.DeweyDecimate.Omnibus.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getHome(Principal p, Model m) {
        m.addAttribute("principal", p);
        return "index";
    }

    @GetMapping("/signup")
    public String getSignup(Principal p, Model m) {
        m.addAttribute("principal", p);
        
        return "signup";
    }

    @GetMapping("/login")
    public String getLoginPage(Principal p, Model m) {
        m.addAttribute("principal", p);

        return "login";
    }

}
