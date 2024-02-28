   package com.equbmember.drawEqub.service;

import com.equbmember.drawEqub.dto.MemberResponseDto;
import com.equbmember.drawEqub.model.Equb;
import com.equbmember.drawEqub.model.Member;
import com.equbmember.drawEqub.repository.EqubMemberRepository;
import com.equbmember.drawEqub.repository.EqubRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MemberService {
    @Autowired
    EqubMemberRepository equbMemberRepository;
    @Autowired
    EqubRepository equbRepository;
    Random random;
    public MemberService() {
        this.random = new Random();
    }
    public ResponseEntity<String> joinToEqub(Long equbId,Member member ) {
        Equb equb = equbRepository.findById(equbId).orElseThrow(() -> new RuntimeException("Equb not found"));
         // Step 2: Obtain the generated identifier of the Equb entity
        Long equbIdentifier = equb.getId();
        if (equb.getCurrentMemberCount() < equb.getNumberOfMembers()){
            equb.setCurrentMemberCount(equb.getCurrentMemberCount()+1);
            equbRepository.save(equb);
            member.setEqub(equb );
            equbMemberRepository.save(member);
            return ResponseEntity.ok("employee is register to equb successfully ");
        }
        return ResponseEntity.badRequest().body("equb fully registered the required number of member , so try other equb");
    }

    public ResponseEntity<String>  pickLuckyWinner(Long equbId) {
        List<Member> eligibleMembers = equbMemberRepository.findByHasWonFalseAndEqubId(equbId);

            // Pick a random winner from the eligible members
        if (!eligibleMembers.isEmpty()) {
            int index = random.nextInt(eligibleMembers.size());
            Member winner = eligibleMembers.get(index);
            winner.setHasWon(true);
            winner.setWinningMonth(getCurrentMonth());
            equbMemberRepository.save(winner);
            return ResponseEntity.ok("the Lucky member" + winner);
        }
        return ResponseEntity.badRequest().body("There is no member ");
    }

        private int getCurrentMonth() {
            // Implement logic to get the current month, e.g., using java.time.Month or java.util.Calendar
            // Return the month as an integer (1 for January, 2 for February, etc.)
            return 0;
        }

    public ResponseEntity<String> getMemberById(Long id) {
        Optional<Member> checkMemberExist = equbMemberRepository.findById(id);
        if(checkMemberExist.isPresent()){
            Member member = checkMemberExist.get();
            MemberResponseDto responseDTO = new MemberResponseDto();
            responseDTO.setEmail(member.getEmail());
            responseDTO.setEmployeeId(member.getEmployeeId());
            responseDTO.setUserName(member.getName());
            return ResponseEntity.ok("the member detail is : "+responseDTO);
        }
        return ResponseEntity.badRequest().body("the equb member with id "+id+ "is not found");
    }

    public List<Member> getMemberByEqubId(Long equbId){
        List<Member> members = equbMemberRepository.findByEqubId(equbId);
        return members.stream().toList();
    }



}

