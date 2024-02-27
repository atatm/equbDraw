package com.equbmember.drawEqub.controller;

import com.equbmember.drawEqub.dto.EqubResponseDto;
import com.equbmember.drawEqub.model.Equb;
import com.equbmember.drawEqub.service.EqubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equb")
public class EqubController {
    @Autowired
    EqubService equbService;
    @PostMapping
    public ResponseEntity<String> addEqub(@RequestBody Equb equb){
        return equbService.createEqub(equb);
    }

    @GetMapping("get")
    public List<Equb> getEqub(){
        return equbService.getEqub();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<String> getEqubById(@PathVariable Long id){
        return equbService.getEqubById(id);
    }

}
