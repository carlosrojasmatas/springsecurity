package com.sszm.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name="contact_messages")
@Data
public class Contact {

    @Id
    private String contactId;

    private String contactName;
    private String contactEmail;
    private String subject;
    private String message;
    private LocalDate createDt;
}
