package org.fdu.awt.minifdustudy.dao;

import org.fdu.awt.minifdustudy.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuizDAO extends JpaRepository<Quiz, Long> {
    Quiz findQuizById(Long id);

    @Query("SELECT q FROM Quiz q ORDER BY RAND() LIMIT 1")
    Quiz getRandomQuiz();
}
