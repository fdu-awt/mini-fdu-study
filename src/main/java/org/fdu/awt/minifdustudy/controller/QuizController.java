package org.fdu.awt.minifdustudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifdustudy.bo.quiz.resp.QuizQuestionResp;
import org.fdu.awt.minifdustudy.dto.QuizDTO;
import org.fdu.awt.minifdustudy.exception.NotExistsException;
import org.fdu.awt.minifdustudy.result.Result;
import org.fdu.awt.minifdustudy.result.ResultFactory;
import org.fdu.awt.minifdustudy.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Violette
 * @date 2024/5/13 18:56
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/study-service")
public class QuizController {
    private final IQuizService quizService;

    @Autowired
    public QuizController(IQuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/get-random-quiz")
    public Result getRandomQuizQuestion() {
        try {
            QuizDTO quizDTO = quizService.getRandomQuizQuestion();
            QuizQuestionResp respData = QuizDTO.toQuestionResp(quizDTO);
            return ResultFactory.buildSuccessResult(respData);
        } catch (NotExistsException e) {
            log.warn("getRandomQuizQuestion error", e);
            return ResultFactory.buildFailResult(e.getMessage());
        } catch (Exception e) {
            log.error("getRandomQuizQuestion error", e);
            return ResultFactory.buildInternalServerErrorResult();
        }
    }


}
