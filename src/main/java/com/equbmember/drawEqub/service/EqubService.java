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

    public ResponseEntity<EqubResponseDto> getEqubById(Long id) {
        Optional<Equb> checkEqubExist = equbRepository.findById(id);
        if(checkEqubExist.isPresent()){
            Equb equb = checkEqubExist.get();
            EqubResponseDto responseDto = new EqubResponseDto();
            responseDto.setEqubName(equb.getEqubName());
            responseDto.setEqubType(equb.getEqubType());
            responseDto.setAmount(equb.getEqubAmount());
            responseDto.setNumberOfMembers(equb.getNumberOfMembers());
            return ResponseEntity.ok(responseDto);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<String> updateEqub(Long id, Equb equb) {
        Optional<Equb> checkEqubExist = equbRepository.findById(id);
        if (checkEqubExist.isPresent()){
           Equb newEqub = checkEqubExist.get();
           newEqub.setEqubName(equb.getEqubName());
           newEqub.setCurrentMemberCount(equb.getCurrentMemberCount());
           newEqub.setStartingDate(equb.getStartingDate());
           equbRepository.save(newEqub);
           return ResponseEntity.ok("equb with id "+id+" is updated successfully");
        }
       return ResponseEntity.badRequest().body("equb with id "+id+ " is not found");
    }
}
