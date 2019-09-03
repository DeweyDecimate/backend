package com.DeweyDecimate.Omnibus.controllers;

import com.DeweyDecimate.Omnibus.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.List;


@Controller
public class BookClubController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    BookClubRepository bookClubRepository;

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/clubs")
    public RedirectView makeClub (String description, String clubName, Principal p){
//  Create new bookclub
        if(bookClubRepository.findByClubName(clubName) == null){
            BookClub bc = new BookClub(description, clubName, "/default-book-cover.png");
            bookClubRepository.save(bc);
//  new membership
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
            Date date = new Date(System.currentTimeMillis());

            Membership membership = new Membership(applicationUser, bc, date);

//  saves application user and bookclub to respective DB
            membershipRepository.save(membership);
        } else{
            System.out.println("This club already exists");
        }
        return new RedirectView("/clubs");
    }

    @GetMapping("/clubs")
    public String getClub(Principal p, Model m){
        List<BookClub> test = bookClubRepository.findAll();
        m.addAttribute("bookclubs",  bookClubRepository.findAll());
        return "clubs";

    }



}
