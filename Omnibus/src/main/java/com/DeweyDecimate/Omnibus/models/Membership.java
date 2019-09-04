package com.DeweyDecimate.Omnibus.models;


import javax.persistence.*;
import java.sql.Date;


@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    ApplicationUser applicationUser;

    @ManyToOne
    BookClub bookClubId;

    Date dateJoined;

    public Membership(ApplicationUser applicationUser, BookClub bookClubId, Date dateJoined) {
        this.applicationUser = applicationUser;
        this.bookClubId = bookClubId;
        this.dateJoined = dateJoined;
    }

    public Membership(){}

    // ---------------------------- Getters & Setters -------------------------------

    public long getId() {
        return id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public BookClub getBookClubId() {
        return bookClubId;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

}
