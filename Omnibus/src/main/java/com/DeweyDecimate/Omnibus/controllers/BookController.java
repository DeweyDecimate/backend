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
    MembershipRepository membershipRepository;

    @Autowired
    BookRepository bookRepository;

    // You've got some common logic here for whether an ApplicationUser is a member of a BookClub.
    // It's cleaner to make that an instance method on a BookClub.
    @PostMapping("/book/{clubId}")
    public RedirectView addBookToClub(String title, String author, String description, String bookImg, @PathVariable long clubId, Principal p){
        ApplicationUser loggedUser = applicationUserRepository.findByUsername(p.getName());
        BookClub currentClub = bookClubRepository.getOne(clubId);
        // checking if something == true is the same as just checking the thing
        if (currentClub.isMember(loggedUser)) {
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
        if (currentClub.isMember(applicationUser)) {
            Book deleteBook = bookRepository.getOne(bookId);
            if(currentClub.getCurrentBook() != null && currentClub.getCurrentBook().equals(deleteBook)) {
                // if you really want to get fancy, you could grab another book from the club's books
                // to set as the current book.
                currentClub.setCurrentBook(null);
            }
            bookRepository.delete(deleteBook);

        }
        return new RedirectView("/clubs/"+ currentClub.getRandomId());
    }
}
