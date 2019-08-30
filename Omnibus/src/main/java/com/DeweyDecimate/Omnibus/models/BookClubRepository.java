package com.DeweyDecimate.Omnibus.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookClubRepository extends JpaRepository<BookClub, Long> {
    public ApplicationUser findByRandomId(long randomId);
}
