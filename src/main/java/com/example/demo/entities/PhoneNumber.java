package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@Data
//@NoArgsConstructor
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

    //-----------------------------


    public PhoneNumber() {
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public PhoneType getType() {
        return type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }
}