package com.equbmember.drawEqub.controller;

import com.equbmember.drawEqub.model.Member;
import com.equbmember.drawEqub.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<String> addMember(@RequestBody Member member) {
        memberService.addMember(member);
        return ResponseEntity.ok("Member added successfully");
    }

    @GetMapping("/draw")
    public ResponseEntity<String> pickLuckyWinner() {
        return memberService.pickLuckyWinner();
    }

}
