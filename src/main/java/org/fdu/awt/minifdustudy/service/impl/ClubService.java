package org.fdu.awt.minifdustudy.service.impl;

import org.fdu.awt.minifdustudy.bo.club.ClubResp;
import org.fdu.awt.minifdustudy.dao.ClubDAO;
import org.fdu.awt.minifdustudy.entity.Club;
import org.fdu.awt.minifdustudy.service.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Violette
 * @date 2024/5/20 12:14
 */
@Service
public class ClubService implements IClubService {
    private final ClubDAO clubDAO;

    @Autowired
    public ClubService(ClubDAO clubDAO) {
        this.clubDAO = clubDAO;
    }

    @Override
    public List<ClubResp> getAllClubData() {
        List<Club> clubList = clubDAO.findAll();
        return Club.toClubResp(clubList);
    }
}
