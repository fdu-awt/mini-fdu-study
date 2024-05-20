package org.fdu.awt.minifdustudy.dao;

import org.fdu.awt.minifdustudy.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryDAO extends JpaRepository<History, Long> {
    List<History> findAll();
}
