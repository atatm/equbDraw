package com.equbmember.drawEqub.controller;

import com.equbmember.drawEqub.model.History;
import com.equbmember.drawEqub.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.FileSystemNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;
    @GetMapping("getEqubWinner/{equbId}")
    public List<History> getEqubWinnerHistory(@PathVariable Long equbId){
        return  historyService.getWinningHistory(equbId);
        //return ResponseEntity.ok("the list of equb winners");
    }
    @GetMapping("getRecentEqubWinner/{equbId}")
    public Page<History> getTheLastTenWinnerHistory(@PathVariable Long equbId){
        return  historyService.getLastRecentWinnerHistory(equbId);
        //return ResponseEntity.ok("the list of equb winners");
    }
}
