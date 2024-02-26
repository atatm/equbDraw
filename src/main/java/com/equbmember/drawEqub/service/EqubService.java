package com.equbmember.drawEqub.service;

import com.equbmember.drawEqub.model.Equb;
import com.equbmember.drawEqub.repository.EqubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EqubService {
    @Autowired
    EqubRepository equbRepository;

    public ResponseEntity<String> createEqub(Equb equb) {
        equbRepository.save(equb);
        return ResponseEntity.ok("equb is added successfully");
    }

    public List<Equb> getEqub() {
        return equbRepository.findAll();
    }
}
