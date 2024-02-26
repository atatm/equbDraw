package com.equbmember.drawEqub.service;

import com.equbmember.drawEqub.model.Enterprise;
import com.equbmember.drawEqub.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {
    @Autowired
    EnterpriseRepository enterpriseRepository;

    public ResponseEntity<String> createEnterprise(Enterprise enterprise) {
        enterpriseRepository.save(enterprise);
        return ResponseEntity.ok("enterprise Added successfully");
    }
}
