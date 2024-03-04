   package com.equbmember.drawEqub.service;

import com.equbmember.drawEqub.dto.MemberResponseDto;
import com.equbmember.drawEqub.model.Equb;
import com.equbmember.drawEqub.model.History;
import com.equbmember.drawEqub.model.Member;
import com.equbmember.drawEqub.repository.EqubMemberRepository;
import com.equbmember.drawEqub.repository.EqubRepository;
import com.equbmember.drawEqub.repository.HistoryRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
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
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    private KafkaTemplate<String , Member> memberKafkaTemplate;

    Random random;
    public MemberService() {
        this.random = new Random();
    }
    public ResponseEntity<String> joinToEqub(Long equbId,Member member ) {
        Equb equb = equbRepository.findById(equbId).orElseThrow(() -> new RuntimeException("Equb not found"));
        if (equb.getCurrentMemberCount() < equb.getNumberOfMembers()){
            equb.setCurrentMemberCount(equb.getCurrentMemberCount()+1);
            member.setEqub(equb );
            equbMemberRepository.save(member);
            equbRepository.save(equb);
            memberKafkaTemplate.send("WINNER_TOPIC",member);
            return ResponseEntity.ok("employee is register to equb successfully ");
        }
        return ResponseEntity.badRequest().body("equb is registered the required number of member , so try another equb");
    }

    public ResponseEntity<String>  pickLuckyWinner(Long equbId) {
        List<Member> eligibleMembers = equbMemberRepository.findByHasWonFalseAndEqubId(equbId);
        History history = new History();

            // Pick a random winner from the eligible members
        if (!eligibleMembers.isEmpty()) {
            int index = random.nextInt(eligibleMembers.size());
            Member winner = eligibleMembers.get(index);
            winner.setHasWon(true);
            equbMemberRepository.save(winner);
            history.setName(winner.getName());
            history.setEqubId(equbId);
            historyRepository.save(history);
            memberKafkaTemplate.send("WINNER_TOPIC",winner);
            return ResponseEntity.ok("the Lucky member" + winner);
        }
        return ResponseEntity.badRequest().body("There is no member ");
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

