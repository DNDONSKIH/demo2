package com.example.demo.controllers;

import com.example.demo.components.ServiceClass;
import com.example.demo.entities.Contact;
import com.example.demo.entities.PhoneNumber;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;



@RestController
public class ContactRestController {

    @Autowired
    ServiceClass serviceClass;

    @Autowired
    ContactRepository contactRepository;

    @PostMapping("/addcontact")
    public Contact addContactViaAjax(@RequestBody Contact contact) {

//        boolean validInputData = serviceClass.isValidName(contact.getSurname())
//                            && serviceClass.isValidName(contact.getMiddleName())
//                            && serviceClass.isValidName(contact.getLastName())
//                            && (contact.getBirthday() != null);
//
//        if(validInputData) {
//            Contact savedContact = contactRepository.save(contact);
//            return contactRepository.findById(savedContact.getId()).orElse(new Contact());
//        }
//
//        var newContact = new Contact();
//        newContact.setId(-1);
//        return newContact;

        Contact savedContact = contactRepository.save(contact);
        return contactRepository.findById(savedContact.getId()).orElse(new Contact());
    }


}

/*
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo2ApplicationTests {

    @Test
    void contextLoads() {
    }

}
*/