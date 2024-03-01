package com.equbmember.drawEqub.model;

import com.equbmember.drawEqub.model.Enum.Interval;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equb")
public class Equb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equbName;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "enterprise_code")
//    private Enterprise enterprise;

//    @ManyToOne
//    @JoinColumn(name = "created_by_id")
//    private User createdBy;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Interval equbType;

    @Column(nullable = false)
    private BigDecimal equbAmount;

    @Column(nullable = false)
    private int numberOfMembers;

    @Column(nullable = false)
    private LocalDate startingDate;

    @Column(nullable = false)
    private LocalDate endDate; // Calculate based on starting_date, interval, and number_of_members

    @Column(nullable = false)
    private int currentMemberCount;

    @JsonManagedReference
    @OneToMany(mappedBy = "equb")
    private List<Member> members;


//    @ManyToMany
//    @JoinTable(name = "employee_equb",
//            joinColumns = @JoinColumn(name = "equb_id"),
//            inverseJoinColumns = @JoinColumn(name = "employee_id"))
//    private List<Employee> members;


//    @JsonBackReference
//    @ManyToOne//(fetch = FetchType.LAZY) // Relationship with Equb
//    @JoinColumn(name = "enterprise_id")
//    private Enterprise enterprise;

}
