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
@Builder
@Table(name = "enterprise")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "enterprise_code")
    private String enterpriseCode;

    @Column(nullable = false)
    private String enterpriseName;

    @Column(nullable = false)
    private String tinNumber;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String enterpriseType;

    @Column(nullable = false)
    private String businessNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

//    @OneToMany(mappedBy = "enterprise") // Map to the Employee's enterprise field
//    private List<Member> members;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "enterprise")
//    private List<Member> members;

    @JsonManagedReference
    @OneToMany(mappedBy = "enterprise")
    private List<Equb> equb;


}
