package com.sszm.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "notice_details")
@Data
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer noticeId;
    private String noticeSummary;
    private String noticeDetails;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createDt;
    private LocalDate updateDt;
}
