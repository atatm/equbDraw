package com.equbmember.drawEqub.controller;

import com.equbmember.drawEqub.model.Equb;
import com.equbmember.drawEqub.model.Member;
import com.equbmember.drawEqub.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/members/{equbId}")
    public ResponseEntity<String> addMember(@PathVariable Long equbId ,@RequestBody Member member) {
        memberService.addMember(member ,equbId);
        return ResponseEntity.ok("Member added successfully");
    }

    @GetMapping("/draw")
    public ResponseEntity<String> pickLuckyWinner(@PathVariable Long equbId) {
        return memberService.pickLuckyWinner(equbId);
    }



}
