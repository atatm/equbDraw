package com.equbmember.drawEqub.service;

import com.equbmember.drawEqub.dto.EqubResponseDto;
import com.equbmember.drawEqub.model.Equb;
import com.equbmember.drawEqub.repository.EqubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<String> getEqubById(Long id) {
        Optional<Equb> checkEqubExist = equbRepository.findById(id);
        if(checkEqubExist.isPresent()){
            Equb equb = checkEqubExist.get();
            EqubResponseDto responseDto = new EqubResponseDto();
            responseDto.setEqubName(equb.getEqubName());
            responseDto.setEqubType(equb.getEqubType());
            responseDto.setAmount(equb.getEqubAmount());
            responseDto.setNumberOfMembers(equb.getNumberOfMembers());
            return ResponseEntity.ok("the equb id with "+id + " is :"+responseDto);
        }
        return ResponseEntity.badRequest().body("the equb with id "+id+" is not found");
    }
}
