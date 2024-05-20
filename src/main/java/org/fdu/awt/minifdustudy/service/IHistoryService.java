package org.fdu.awt.minifdustudy.service;

import org.fdu.awt.minifdustudy.bo.history.HistoryResp;

import java.util.List;

public interface IHistoryService {
    List<HistoryResp> getAllHistoryData();
}
