package com.DeweyDecimate.Omnibus.controllers;

import com.DeweyDecimate.Omnibus.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;


@Controller
public class BookController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    BookClubRepository bookClubRepository;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/book/{clubId}")
    public RedirectView addBookToClub(String title, String author, String description, String bookImg, @PathVariable long clubId, Principal p){
        ApplicationUser loggedUser = applicationUserRepository.findByUsername(p.getName());
        BookClub currentClub = bookClubRepository.getOne(clubId);
        boolean isClubMember = false;
        for(Membership m : currentClub.getMemberships()){
            if(m.getApplicationUser().getId() == loggedUser.getId()){
                isClubMember = true;
                break;
            }
        }

        if (isClubMember == true) {
            Book newBook = new Book(title, author, description, bookImg, currentClub);
            bookRepository.save(newBook);
            currentClub.setCurrentBook(newBook);
            bookClubRepository.save(currentClub);
        }
        return new RedirectView("/clubs/" + currentClub.getRandomId());
    }

    @DeleteMapping("/book/{clubId}")
    public RedirectView deleteBook(long bookId, @PathVariable long clubId, Principal p){
        BookClub currentClub = bookClubRepository.getOne(clubId);
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
        for(Membership m : currentClub.getMemberships()){
            if(m.getApplicationUser().getId() == applicationUser.getId()){
                Book deleteBook = bookRepository.getOne(bookId);
                if(currentClub.getCurrentBook() != null && currentClub.getCurrentBook().equals(deleteBook)) {
                    currentClub.setCurrentBook(null);
                }
                bookRepository.delete(deleteBook);

            }
        }
        return new RedirectView("/clubs/"+ currentClub.getRandomId());
    }
}
