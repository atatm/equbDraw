package com.equbmember.drawEqub.controller;

import com.equbmember.drawEqub.model.Equb;
import com.equbmember.drawEqub.model.Member;
import com.equbmember.drawEqub.repository.EqubRepository;
import com.equbmember.drawEqub.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/addMember/{equbId}")
    public ResponseEntity<String> addMember(@PathVariable Long equbId ,@RequestBody Member member) {
        memberService.addMember(equbId ,member);
        return ResponseEntity.ok("Member added successfully");
    }

    @PostMapping("/getMember/{id}")
    public ResponseEntity<String> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/getMember/{equbId}")
    public List<Member> getMemberByEqubId(@PathVariable Long equbId) {
        return memberService.getMemberByEqubId(equbId);
    }

    @GetMapping("/draw/{equbId}")
    public ResponseEntity<String> pickLuckyWinner(@PathVariable Long equbId) {

        return memberService.pickLuckyWinner(equbId);
    }





}
