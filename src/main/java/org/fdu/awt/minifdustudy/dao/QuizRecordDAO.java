package org.fdu.awt.minifdustudy.dao;

import org.fdu.awt.minifdustudy.entity.QuizRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRecordDAO extends JpaRepository<QuizRecord, Long> {
}