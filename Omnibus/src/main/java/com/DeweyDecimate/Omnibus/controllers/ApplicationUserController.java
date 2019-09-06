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

            return new RedirectView("/taken");
        }
    }

    // I'm so glad that these use the same template!
    // The last thing I would change would be to unify a little more of this code:
    private String showProfile(Principal p, ApplicationUser viewedUser, ApplicationUser loggedUser, Model m) {
        m.addAttribute("principal", p);
        m.addAttribute("viewedUser", viewedUser);
        m.addAttribute("loggedUser", loggedUser);
        return "myprofile";
    }
    @GetMapping("/myprofile")
    public String getMyprofile(Principal p, Model m){
        return showProfile(p, applicationUserRepository.findByUsername(p.getName()), applicationUserRepository.findByUsername(p.getName()), m);
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable long id, Principal p, Model m){
        return showProfile(p, applicationUserRepository.findById(id).get(), applicationUserRepository.findByUsername(p.getName()), m);
    }

    @PutMapping("/users/pic")
    public RedirectView updatePic(String imageURL, Principal p){
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
        applicationUser.setUserImg(imageURL);
        applicationUserRepository.save(applicationUser);
        return new RedirectView("/myprofile");
    }
}
