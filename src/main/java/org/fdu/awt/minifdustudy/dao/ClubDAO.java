package org.fdu.awt.minifdustudy.dao;

import org.fdu.awt.minifdustudy.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubDAO extends JpaRepository<Club, Long> {
    List<Club> findAll();
}
