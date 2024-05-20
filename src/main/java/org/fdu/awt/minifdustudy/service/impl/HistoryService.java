package org.fdu.awt.minifdustudy.service.impl;

import org.fdu.awt.minifdustudy.bo.history.HistoryResp;
import org.fdu.awt.minifdustudy.dao.HistoryDAO;
import org.fdu.awt.minifdustudy.entity.History;
import org.fdu.awt.minifdustudy.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Violette
 * @date 2024/5/20 11:49
 */
@Service
public class HistoryService implements IHistoryService {
    private final HistoryDAO historyDAO;

    @Autowired
    public HistoryService(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    @Override
    public List<HistoryResp> getAllHistoryData() {
        List<History> historyList = historyDAO.findAll();
        return History.toHistoryResp(historyList);
    }
}
