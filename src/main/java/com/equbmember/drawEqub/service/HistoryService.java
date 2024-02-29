package com.equbmember.drawEqub.service;

import com.equbmember.drawEqub.model.History;
import com.equbmember.drawEqub.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystemNotFoundException;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;

    public List<History> getWinningHistory(Long equbId) {
        List<History> listOfWinner = historyRepository.findByEqubId(equbId);
        return listOfWinner.stream().toList();
    }

    public Page<History> getLastRecentWinnerHistory(Long equbId) {
        List<History> checkEqubHistoryExist = historyRepository.findByEqubId(equbId);
        if(checkEqubHistoryExist.isEmpty()){
            throw new FileSystemNotFoundException("No history found for equbId: " + equbId);
        }
        Pageable pageable = PageRequest.of(0, 3);
        return historyRepository.findByEqubIdOrderByEqubDrawDateDesc(equbId, pageable);
    }
}
