package com.DeweyDecimate.Omnibus.controllers;

import com.DeweyDecimate.Omnibus.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.sql.Date;


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

    @Autowired
    ClubDiscussionRepository clubDiscussionRepository;

    @PostMapping("/clubs")
    public RedirectView makeClub (String description, String clubName, Principal p){
//  Create new bookclub
        String randomId = null;
        if(bookClubRepository.findByClubName(clubName) == null){
            BookClub bc = new BookClub(description, clubName, "/default-book-cover.png");
            bookClubRepository.save(bc);
            randomId = bc.getRandomId();
//  new membership
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
            Date date = new Date(System.currentTimeMillis());

            Membership membership = new Membership(applicationUser, bc, date);

//  saves application user and bookclub to respective DB
            membershipRepository.save(membership);
        } else{
            System.out.println("This club already exists");
        }
        return new RedirectView("/clubs/" + randomId);
    }

    @GetMapping("/clubs")
    public String getClub(Principal p, Model m){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("principal", p);
        m.addAttribute("bookclubs", bookClubRepository.findAll());
        m.addAttribute("loggedUser", currentUser);
        return "clubs";

    }


    @GetMapping("/clubs/{randomId}")
    public String getSpecificClub(@PathVariable String randomId, Principal p, Model m){
        BookClub bookClub = bookClubRepository.findByRandomId(randomId);
        m.addAttribute("principal", p);
        m.addAttribute("currentClub", bookClub);
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("loggedUser", applicationUser);

        return "oneClub";
    }


    @PostMapping("/clubs/membership")
    public RedirectView joinClub(String randomId, Principal p, Model m){
        BookClub bookClub = bookClubRepository.findByRandomId(randomId);
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
        Membership membership = new Membership(applicationUser, bookClub, new Date(System.currentTimeMillis()));
        membershipRepository.save(membership);

        return new RedirectView("/clubs/" + randomId);
    }

    //TODO update bookclub information
    @GetMapping("/discussion/{randomId}")
    public String getClubDiscussion(@PathVariable String randomId, Principal p, Model m){
        BookClub bookClub = bookClubRepository.findByRandomId(randomId);
        m.addAttribute("principal", p);
        m.addAttribute("currentClub", bookClub);
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("loggedUser", applicationUser);
        return "makepost";
    }
    @PostMapping("/discussion")
    public RedirectView makeDiscussion(String content, Long userId, Long clubId){
        BookClub club = bookClubRepository.getOne(clubId);
        ApplicationUser user = applicationUserRepository.getOne(userId);
        ClubDiscussion discussion = new ClubDiscussion(content,user,club);
        clubDiscussionRepository.save(discussion);
        return new RedirectView("/clubs/" + club.getRandomId());
    }
}
