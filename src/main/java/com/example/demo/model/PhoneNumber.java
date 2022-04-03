package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="contact_id")
    private Contact contact;

    private String value;
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @JsonIgnore
    public Contact getContact() { return contact; }
}