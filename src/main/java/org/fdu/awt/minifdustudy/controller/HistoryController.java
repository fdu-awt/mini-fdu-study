package org.fdu.awt.minifdustudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifdustudy.bo.history.HistoryResp;
import org.fdu.awt.minifdustudy.bo.quiz.resp.QuizQuestionResp;
import org.fdu.awt.minifdustudy.dto.QuizDTO;
import org.fdu.awt.minifdustudy.exception.NotExistsException;
import org.fdu.awt.minifdustudy.result.Result;
import org.fdu.awt.minifdustudy.result.ResultFactory;
import org.fdu.awt.minifdustudy.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Violette
 * @date 2024/5/20 12:04
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/study-service")
public class HistoryController {
    private final IHistoryService historyService;

    @Autowired
    public HistoryController(IHistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/get-all-history-data")
    public Result getAllHistoryData() {
        try {
            List<HistoryResp> respData = historyService.getAllHistoryData();
            return ResultFactory.buildSuccessResult(respData);
        } catch (RuntimeException e) {
            log.error("getAllHistoryData error", e);
            return ResultFactory.buildInternalServerErrorResult();
        }
    }
}
