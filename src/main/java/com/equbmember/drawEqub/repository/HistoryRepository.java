package com.equbmember.drawEqub.repository;

import com.equbmember.drawEqub.model.History;
import com.equbmember.drawEqub.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History,Long> {
    List<History> findByEqubId(Long equbId);
    //List<History> findByEqubIdAndTop10ByOrderByEqubDrawDateDesc(Pageable pageable,Long equb);
    Page<History> findByEqubIdOrderByEqubDrawDateDesc(Long equbId, Pageable pageable);



   // List<History> findByEqubIdOrderByEqubDrawDateDesc(Long equb, Pageable pageable);
   // List<History> findByEqubIdAndTop10ByOrderByEqubDrawDateDesc(Long equb, Pageable pageable);
}
