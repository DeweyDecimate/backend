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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public RedirectView createUser(String username, String password, String firstname, String lastname){
        if (applicationUserRepository.findByUsername(username) == null) {
            ApplicationUser newUser = new ApplicationUser(username,
                    passwordEncoder.encode(password),
                    firstname,
                    lastname);

            Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            applicationUserRepository.save(newUser);

            return new RedirectView("/myprofile");

        } else {
//            CR: redirect to sign up with a model that says that the username is taken
//            Make the message hidden if the name is not taken and show when the name is taken
            return new RedirectView("/taken");
        }
    }

//    CR: is the principal the same user as loggedUser? Maybe this can be reduced to one attribute setup?
    @GetMapping("/myprofile")
    public String getMyprofile(Principal p, Model m){
        m.addAttribute("principal", p);
        m.addAttribute("loggedUser", applicationUserRepository.findByUsername(p.getName()));
        m.addAttribute("viewedUser", applicationUserRepository.findByUsername(p.getName()));
        return "myprofile";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable long id, Principal p, Model m){
        ApplicationUser viewedUser = applicationUserRepository.findById(id).get();
        m.addAttribute("principal", p);
        m.addAttribute("viewedUser", viewedUser);
        m.addAttribute("loggedUser", applicationUserRepository.findByUsername(p.getName()));
        return "myprofile";
    }

    @PutMapping("/users/pic")
    public RedirectView updatePic(String imageURL, Principal p){
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
        applicationUser.setUserImg(imageURL);
        applicationUserRepository.save(applicationUser);
        return new RedirectView("/myprofile");
    }
}
