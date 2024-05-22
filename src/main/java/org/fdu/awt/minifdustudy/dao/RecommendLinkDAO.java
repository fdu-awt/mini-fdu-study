package org.fdu.awt.minifdustudy.dao;

import org.fdu.awt.minifdustudy.entity.RecommendLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendLinkDAO extends JpaRepository<RecommendLink, Long> {
    @Query("SELECT r FROM RecommendLink r ORDER BY RAND() LIMIT 3")
    List<RecommendLink> getRandomRecommendLinks();
}
