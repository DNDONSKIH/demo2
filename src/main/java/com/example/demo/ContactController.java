package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public  String index() {
        return "index";
    }

    @GetMapping("/list")
    public @ResponseBody Iterable<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/list/{id}")
    public @ResponseBody Contact getContactsById(@PathVariable Integer id) {
        return contactRepository.findById(id).orElse(new Contact());
    }

    @GetMapping("/surname")
    public @ResponseBody List<Contact> getContactByName(@RequestParam(name="surname") String surname) {
        return contactRepository.findBySurname(surname);
    }

    @GetMapping("/phone-number")
    public @ResponseBody List<Contact> getContactByPhoneNumber(@RequestParam(name="number") String number) {
        return contactRepository.findByPhoneNumberList_Value(number);
    }

    @GetMapping("/add")
    public @ResponseBody String add() {
        var newContact = new Contact();
        newContact.setSurname("111");
        newContact.setMiddleName("234");
        newContact.setLastName("345");
        newContact.setBirthday(new Date());

        var phonenumber1 = new PhoneNumber();
        phonenumber1.setValue("11111122233331");
        phonenumber1.setType(PhoneType.WORK);

        var phonenumber2 = new PhoneNumber();
        phonenumber2.setValue("22222");
        phonenumber2.setType(PhoneType.CELLPHONE);

        var numlist = new ArrayList<PhoneNumber>();
        numlist.add(phonenumber1);
        numlist.add(phonenumber2);
        newContact.setPhoneNumberList( numlist );
        contactRepository.save(newContact);
        return "Saved!";
    }




//    @PostMapping("/add")
//    public @ResponseBody String addNewUser (@RequestParam(name="name", required=false, defaultValue="Ivan") String name) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        Contact n = new Contact();
//        n.setSurname(name);
//        n.setLastname("123");
//        n.setMiddlename("123");
//        n.setPhoneId(1);
//        n.setBirthday(new Date());
//        contactRepository.save(n);
//        return "Saved";
//    }

}