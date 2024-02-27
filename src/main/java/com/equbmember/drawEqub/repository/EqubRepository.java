package com.equbmember.drawEqub.repository;

import com.equbmember.drawEqub.model.Equb;
import com.equbmember.drawEqub.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EqubRepository extends JpaRepository<Equb,Long> {

}
