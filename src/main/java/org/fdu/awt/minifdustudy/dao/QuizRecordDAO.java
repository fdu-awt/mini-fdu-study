package org.fdu.awt.minifdustudy.dao;

import org.fdu.awt.minifdustudy.entity.QuizRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface QuizRecordDAO extends JpaRepository<QuizRecord, Long> {
    List<QuizRecord> findAllByUserId(Long userId);

    // JPQL，面向实体对象的查询语言，可以使用类和属性名来编写查询（无需用数据库表和列名）
    @Query("SELECT q FROM QuizRecord q WHERE q.userId = :userId AND q.createTimestamp >= :fromTime")
    List<QuizRecord> findQuizRecordFromTime(@Param("userId") Long userId, @Param("fromTime") Timestamp fromTime);
}