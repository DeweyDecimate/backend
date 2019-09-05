package com.DeweyDecimate.Omnibus.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    String username;

    String password;
    String firstName;
    String lastName;
    String userImg;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "applicationUser")
    Set<Membership> memberships;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "discussionUser")
    List<ClubDiscussion> discussions;

    // ---------------------------- Constructors -------------------------------

    public ApplicationUser(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userImg = "/default-avatar.png";
    }

    public ApplicationUser(){}


    // ---------------------------- Getters & Setters -------------------------------

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserImg() {
        return userImg;
    }

    public Set<Membership> getMemberships() {
        return memberships;
    }

    // ---------------------------- Methods -------------------------------

    public String toString() {
        return String.format("%s (%s %s)", this.username, this.firstName, this.lastName);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
