package com.DeweyDecimate.Omnibus.models;


import javax.persistence.*;
import java.sql.Date;


@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    ApplicationUser userId;

    @ManyToOne
    BookClub bookClubId;

    Date dateJoined;




    public Membership(ApplicationUser userId, BookClub bookClubId, Date dateJoined) {
        this.userId = userId;
        this.bookClubId = bookClubId;
        this.dateJoined = dateJoined;
    }

    public Membership(){}





    public long getId() {
        return id;
    }

    public ApplicationUser getUserId() {
        return userId;
    }

    public BookClub getBookClubId() {
        return bookClubId;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

}
