package com.sszm.repository;

import com.sszm.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice, Long>{

    @Query("SELECT n FROM Notice n WHERE current_date BETWEEN n.startDate AND n.endDate")
    List<Notice> findAllActiveNotices();
}
