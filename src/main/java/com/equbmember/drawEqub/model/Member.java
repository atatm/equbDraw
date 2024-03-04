package com.equbmember.drawEqub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity   
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String employeeId;
    private double contributionAmount;
    private boolean hasWon;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY) // Relationship with Equb
    @JoinColumn(name = "equb_id")
    private Equb equb;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY) // Relationship with Enterprise
//    @JoinColumn(name = "enterprise_id")
//    private Enterprise enterprise;

}
