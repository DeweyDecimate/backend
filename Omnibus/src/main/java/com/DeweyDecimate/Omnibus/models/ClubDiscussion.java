package com.DeweyDecimate.Omnibus.models;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.Date;

@Entity
public class ClubDiscussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    Date timeStamp;
    String content;

    //User Who Posted It or Membership?
    @ManyToOne
    ApplicationUser discussionUser;
    //Club That it belongs to
    @ManyToOne
    BookClub bookClub;

    public ClubDiscussion(String content, ApplicationUser discussionUser, BookClub bookClub){
        this.timeStamp = new java.sql.Date(System.currentTimeMillis());
        this.content = content;
        this.discussionUser = discussionUser;
        this.bookClub = bookClub;
    }
    public ClubDiscussion(){}

    public long getId() {
        return id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getContent() {
        return content;
    }

    public ApplicationUser getDiscussionUser() {
        return discussionUser;
    }

    public BookClub getBookClub() {
        return bookClub;
    }
}
