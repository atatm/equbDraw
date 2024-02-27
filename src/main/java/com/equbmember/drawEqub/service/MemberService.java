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
    public void addMember(Member member , Long equbId) {
        Equb equb = equbRepository.findById(equbId).orElseThrow(() -> new RuntimeException("Equb not found"));
        //Employee employeeExist = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("you are not legal to register to this equb"));
        // Step 2: Obtain the generated identifier of the Equb entity
        Long equbIdentifier = equb.getId();

        member.setEqub(equb );
        equbMemberRepository.save(member);
    }

    public ResponseEntity<String>  pickLuckyWinner(Long equbId) {
            //List<Member> newMember = equbRepository.findById(equb.getId()).get().getMembers();

          //Optional<Equb> equbNew = equbRepository.findById(equbId); //.orElseThrow(() -> new RuntimeException("Equb not found"));
           // Optional<Member> checkMemberByEqubId = equbMemberRepository.findById(equbId);
            // Fetch all members who have not yet won
           // Optional<Member> eligibleEqub= equbRepository.findById(equbId);
          //  List<Member> member = equb.get().getMembers();
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

        //return ResponseEntity.badRequest().body("equb not found "); // No eligible members left

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



}

