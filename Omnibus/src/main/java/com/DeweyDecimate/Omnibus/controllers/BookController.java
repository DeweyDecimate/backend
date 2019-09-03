package com.DeweyDecimate.Omnibus.controllers;


import com.DeweyDecimate.Omnibus.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class BookController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    BookClubRepository bookClubRepository;

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/book")
    public RedirectView addBookToClub(String title, String author, String description, String bookImg, long bookClubId, String randomId){
        BookClub currentClub = bookClubRepository.getOne(bookClubId);
        Book newBook = new Book(title,author,description,bookImg,currentClub);
        bookRepository.save(newBook);




        return new RedirectView("/clubs/"+randomId);
    }
}
