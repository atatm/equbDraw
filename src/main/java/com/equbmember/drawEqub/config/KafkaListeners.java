package com.equbmember.drawEqub.config;


import com.equbmember.drawEqub.model.Member;
import com.equbmember.drawEqub.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
public class KafkaListeners {
    @Autowired
    EmailService emailService;
    @KafkaListener(topics ="WINNER_TOPIC", groupId = "group1",
            containerFactory = "objectListenerFactory")
    void winnerListener(Member winner) {
        emailService.sendEmailToWinner(winner.getEmail(), "The Equb draw is has been done", String.format("Dear %s, \n Congratulation you won the equb.", winner.getName()));

    }
    @KafkaListener(topics ="MEMBER_TOPIC", groupId = "group1",
            containerFactory = "memberListenerFactory")
    void memberListener(Member member) {
        emailService.sendEmailToWinner(member.getEmail(), "Member registered", String.format("Dear %s, \n you successfully registered to the equb.", member.getName()));

    }
//    @KafkaListener(topics ="EMPLOYEE_TOPIC", groupId = "group1",
//            containerFactory = "objectListenerFactory")
//    void employeeListener(Member member) {
//        //System.out.println("Recieved " + winner + "㊗");
//        emailService.sendEmailToWinner(member.getEmail(), "Member registered", String.format("Dear %s, \n you successfully registered to the equb.", member.getName()));
//
//    }
}