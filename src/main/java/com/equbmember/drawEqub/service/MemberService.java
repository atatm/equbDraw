   package com.equbmember.drawEqub.service;

import com.equbmember.drawEqub.model.Member;
import com.equbmember.drawEqub.repository.EqubMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MemberService {
    @Autowired
    EqubMemberRepository equbMemberRepository;
    Random random;
    public MemberService() {
        this.random = new Random();
    }
    public void addMember(Member member) {
        equbMemberRepository.save(member);
    }

    public ResponseEntity<String>  pickLuckyWinner() {
            // Fetch all members who have not yet won
        List<Member> eligibleMembers = equbMemberRepository.findByParticipatedFalse();

            // Pick a random winner from the eligible members
        if (!eligibleMembers.isEmpty()) {
            int index = random.nextInt(eligibleMembers.size());
            Member winner = eligibleMembers.get(index);
            winner.setParticipated(true);
            winner.setWinningMonth(getCurrentMonth());
            equbMemberRepository.save(winner);
            return ResponseEntity.ok("the Lucky member"+winner);
        }
        return ResponseEntity.badRequest().body("There is no member "); // No eligible members left
    }

        private int getCurrentMonth() {
            // Implement logic to get the current month, e.g., using java.time.Month or java.util.Calendar
            // Return the month as an integer (1 for January, 2 for February, etc.)
            return 0;
        }
    }

