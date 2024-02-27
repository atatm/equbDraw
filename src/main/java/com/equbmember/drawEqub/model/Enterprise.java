package com.equbmember.drawEqub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enterprise")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "enterprise_code")
    private String enterpriseCode;

    private String enterpriseName;


    private String tinNumber;


    private String owner;


    private String enterpriseType;


    private String businessNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

//    @OneToMany(mappedBy = "enterprise") // Map to the Employee's enterprise field
//    private List<Member> members;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "enterprise")
//    private List<Member> members;
//
//    @JsonManagedReference
//    @OneToMany(mappedBy = "enterprise")
//    private List<Equb> equb;


}
