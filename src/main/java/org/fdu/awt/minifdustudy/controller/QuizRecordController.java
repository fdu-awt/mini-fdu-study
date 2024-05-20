package org.fdu.awt.minifdustudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifdustudy.bo.quiz.resp.QuizQuestionResp;
import org.fdu.awt.minifdustudy.bo.record.req.QuizAnswerReq;
import org.fdu.awt.minifdustudy.bo.record.resp.QuizAnswerResp;
import org.fdu.awt.minifdustudy.dto.QuizDTO;
import org.fdu.awt.minifdustudy.dto.QuizRecordDTO;
import org.fdu.awt.minifdustudy.entity.QuizRecord;
import org.fdu.awt.minifdustudy.exception.NotExistsException;
import org.fdu.awt.minifdustudy.result.Result;
import org.fdu.awt.minifdustudy.result.ResultFactory;
import org.fdu.awt.minifdustudy.service.IQuizRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Violette
 * @date 2024/5/14 1:36
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/study-service")
public class QuizRecordController {
    private final IQuizRecordService quizRecordService;

    @Autowired
    public QuizRecordController(IQuizRecordService quizRecordService) {
        this.quizRecordService = quizRecordService;
    }

    @PostMapping("/create-new-quiz-record")
    public Result createNewQuizRecord(@Validated @RequestBody QuizAnswerReq quizAnswerReq) {
        try {
            QuizRecordDTO quizRecordDTO = quizRecordService.createNewQuizRecord(quizAnswerReq);
            QuizAnswerResp respData = QuizRecordDTO.toQuizAnswerResp(quizRecordDTO);
            return ResultFactory.buildSuccessResult(respData);
        } catch (NotExistsException e) {
            log.warn("createNewQuizRecord error, quizAnswerReq:{}", quizAnswerReq, e);
            return ResultFactory.buildFailResult(e.getMessage());
        } catch (RuntimeException e) {
            log.warn("createNewQuizRecord error, quizAnswerReq:{}", quizAnswerReq, e);
            return ResultFactory.buildInternalServerErrorResult();
        }
    }

    @GetMapping("/get-all-quiz-record")
    public Result getAllQuizRecord() {
        try {
            List<QuizRecord> respData = quizRecordService.getAllQuizRecord();
            return ResultFactory.buildSuccessResult(respData);
        } catch (RuntimeException e) {
            log.error("getAllQuizRecord error", e);
            return ResultFactory.buildInternalServerErrorResult();
        }
    }
}
