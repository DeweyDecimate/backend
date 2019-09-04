package com.DeweyDecimate.Omnibus.controllers;

import com.DeweyDecimate.Omnibus.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


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
    public RedirectView addBookToClub(String title, String author, String description, String bookImg, long bookClubId, long userId){
        BookClub currentClub = bookClubRepository.getOne(bookClubId);
        ApplicationUser applicationUser = applicationUserRepository.getOne(userId);
        for(Membership m : currentClub.getMemberships()){
            if(m.getApplicationUser().getId() == userId){
                Book newBook = new Book(title,author,description,bookImg,currentClub);
                bookRepository.save(newBook);
                currentClub.setCurrentBook(newBook);
                bookClubRepository.save(currentClub);
            }
        }
        return new RedirectView("/clubs/" + currentClub.getRandomId());
    }

    @DeleteMapping("/book")
    public RedirectView deleteBook(long bookId, long userId, long bookClubId){
        BookClub currentClub = bookClubRepository.getOne(bookClubId);
        ApplicationUser applicationUser = applicationUserRepository.getOne(userId);
        for(Membership m : currentClub.getMemberships()){
            if(m.getApplicationUser().getId() == userId){
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
