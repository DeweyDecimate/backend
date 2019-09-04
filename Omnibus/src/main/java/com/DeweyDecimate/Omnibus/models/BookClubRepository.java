package com.DeweyDecimate.Omnibus.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookClubRepository extends JpaRepository<BookClub, Long> {
    public BookClub findByRandomId(String randomId);
    public BookClub findByClubName(String clubName);
}
