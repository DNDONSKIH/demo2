package com.example.demo.controllers;

import com.example.demo.components.ServiceClass;
import com.example.demo.entities.Contact;
import com.example.demo.entities.PhoneNumber;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
/*
@RestController
public class ContactRestController {

    @Autowired
    ServiceClass serviceClass;

    @Autowired
    ContactRepository contactRepository;

    @PostMapping("/contactlist")
    public Contact addContactViaAjax(   @RequestParam("surname") String surname,
                                        @RequestParam("middlename") String middlename,
                                        @RequestParam("lastname") String lastname,
                                        @RequestParam("birthday") String birthday ) {

        var newContact = new Contact();
        boolean validInputData = serviceClass.isValidName(surname);
        validInputData &= serviceClass.isValidName(middlename);
        validInputData &= serviceClass.isValidName(lastname);

        if(validInputData) {
            Date date = serviceClass.getDateFromDateString(birthday);
            newContact.setSurname(surname);
            newContact.setMiddleName(middlename);
            newContact.setLastName(lastname);
            newContact.setBirthday(date);
            newContact.setPhoneNumberList( new ArrayList<PhoneNumber>() );
            Contact savedContact = contactRepository.save(newContact);
            return contactRepository.findById(savedContact.getId()).orElse(new Contact());
        }

        newContact.setId(-1);
        return newContact;
    }

}
*/



@RestController
public class ContactRestController {

    @Autowired
    ServiceClass serviceClass;

    @Autowired
    ContactRepository contactRepository;

    @PostMapping("/addcontact")
    public Contact addContactViaAjax(@RequestBody Contact contact) {

        boolean validInputData = serviceClass.isValidName(contact.getSurname());
        validInputData &= serviceClass.isValidName(contact.getMiddleName());
        validInputData &= serviceClass.isValidName(contact.getLastName());
        validInputData &= (contact.getBirthday() != null);

        if(validInputData) {
            Contact savedContact = contactRepository.save(contact);
            return contactRepository.findById(savedContact.getId()).orElse(new Contact());
        }

        var newContact = new Contact();
        newContact.setId(-1);
        return newContact;
    }

}