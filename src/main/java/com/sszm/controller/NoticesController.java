package com.sszm.controller;

import com.sszm.model.Notice;
import com.sszm.repository.CustomerRepository;
import com.sszm.repository.NoticeRepository;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("v1/notices")
public class NoticesController {

    private final NoticeRepository noticeRepository;
    private final CustomerRepository customerRepository;


    public NoticesController(NoticeRepository noticeRepository, CustomerRepository customerRepository) {
        this.noticeRepository = noticeRepository;
        this.customerRepository = customerRepository;
    }


    @GetMapping("") // Base path for all endpoints
    public ResponseEntity<List<?>> getNotices(@RequestParam String cust) {
        if(cust.equals("customer")){
            var customer = customerRepository
                    .findById(UUID.fromString("03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5")).get();
            customer.getAuthorities();
            return ResponseEntity.ok().body(List.of(customer));
        }
        var notices = noticeRepository.findAllActiveNotices();
        if (!notices.isEmpty()) {
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        } else {
            return ResponseEntity.ok(notices);
        }
    }

}
