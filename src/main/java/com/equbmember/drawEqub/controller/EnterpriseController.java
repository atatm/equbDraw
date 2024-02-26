package com.equbmember.drawEqub.controller;

import com.equbmember.drawEqub.model.Enterprise;
import com.equbmember.drawEqub.service.EnterpriseService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/enterprise")
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;

    @PostMapping
    public ResponseEntity<String> addEnterprise(@RequestBody Enterprise enterprise){
        return enterpriseService.createEnterprise(enterprise);
    }

}
