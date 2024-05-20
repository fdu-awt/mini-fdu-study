package org.fdu.awt.minifdustudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.fdu.awt.minifdustudy.bo.club.ClubResp;
import org.fdu.awt.minifdustudy.result.Result;
import org.fdu.awt.minifdustudy.result.ResultFactory;
import org.fdu.awt.minifdustudy.service.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Violette
 * @date 2024/5/20 12:19
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/study-service")
public class ClubController {
    private final IClubService clubService;

    @Autowired
    public ClubController(IClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/get-all-club-data")
    public Result getAllClubData() {
        try {
            List<ClubResp> respData = clubService.getAllClubData();
            return ResultFactory.buildSuccessResult(respData);
        } catch (RuntimeException e) {
            log.error("getAllClubData error", e);
            return ResultFactory.buildInternalServerErrorResult();
        }
    }
}
