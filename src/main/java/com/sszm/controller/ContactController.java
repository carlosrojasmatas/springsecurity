package com.sszm.controller;

import com.sszm.model.Contact;
import com.sszm.repository.ContactRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("v1/contact")
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("save")
    public Contact saveContact(@RequestBody Contact contact){
        contact.setContactId(UUID.randomUUID().toString());
        contact.setCreateDt(LocalDate.now());
        return contactRepository.save(contact);
    }

    @GetMapping("hello")
    public String hello(){
        return "world";
    }


}
