package com.equbmember.drawEqub.repository;

import com.equbmember.drawEqub.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EqubMemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByHasWonFalse();
}
