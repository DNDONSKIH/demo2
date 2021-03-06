package com.example.demo.repository;

import com.example.demo.entities.Contact;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
    List<Contact> findBySurname(String surname);
    //List<Contact> findBySurnameStartingWith(String surname);
    List<Contact> findByPhoneNumberList_Value(String phoneNumber);
}