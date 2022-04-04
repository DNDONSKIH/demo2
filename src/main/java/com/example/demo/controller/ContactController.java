package com.example.demo.controller;

import com.example.demo.entities.Contact;
import com.example.demo.entities.PhoneNumber;
import com.example.demo.entities.PhoneType;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("/")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @GetMapping("/contacts")
    public String getContacts(Model model) {
        Iterable<Contact> contactIterable = contactRepository.findAll();
        var contacts = new ArrayList<Contact>();
        for(Contact contact : contactIterable) {
            contacts.add(contact);
        }
        model.addAttribute("contacts", contacts);
        return "contacts";
    }

    @PostMapping("/contacts")
    public String addNewContact(@RequestParam("surname") String surname,
                                @RequestParam("middlename") String middlename,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("birthday") String birthday,
                                Model model) {

        String [] dateSubstring = birthday.split("-");
        int dateYearNum = Integer.parseInt(dateSubstring[0]);
        int dateMonthNum = Integer.parseInt(dateSubstring[1]);
        int dateDayNum = Integer.parseInt(dateSubstring[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, dateYearNum);
        cal.set(Calendar.MONTH, dateMonthNum);
        cal.set(Calendar.DAY_OF_MONTH, dateDayNum);
        Date date = cal.getTime();

        var newContact = new Contact();
        var numList = new ArrayList<PhoneNumber>();
        newContact.setSurname(surname);
        newContact.setMiddleName(middlename);
        newContact.setLastName(lastname);
        newContact.setBirthday(date);

        newContact.setPhoneNumberList( numList );
        contactRepository.save(newContact);

        Iterable<Contact> contactIterable = contactRepository.findAll();
        var contacts = new ArrayList<Contact>();
        for(Contact contact : contactIterable) {
            contacts.add(contact);
        }
        model.addAttribute("contacts", contacts);

        return "contacts";
    }

    @PostMapping("/contacts/{id}")
    public String addPhoneNumberById(@PathVariable Integer id,
                                     @RequestParam(name = "value", required = false, defaultValue = "") String requestValue,
                                     @RequestParam(name = "phone-type", required = false, defaultValue = "cellphone") String requestPhoneType,
                                     Model model) {
        Contact contact = contactRepository.findById(id).orElse(new Contact());
        //contactRepository.deleteById(id);

        PhoneType phoneType;
        switch (requestPhoneType) {
            case "home":
                phoneType = PhoneType.HOME;
                break;
            case "cellphone":
                phoneType = PhoneType.CELLPHONE;
                break;
            default:
                phoneType = PhoneType.WORK;
                break;
        }

        var newPhoneNumber = new PhoneNumber();
        newPhoneNumber.setValue(requestValue);
        newPhoneNumber.setType(phoneType);
        newPhoneNumber.setContact(contact);

        var numlist = contact.getPhoneNumberList();
        numlist.add(newPhoneNumber);
        contact.setPhoneNumberList( numlist );
        contactRepository.save(contact);

        model.addAttribute("contact", contact);
        return "redirect:/contacts/" + id.toString();
    }

    @PostMapping("/contacts/del/{id}")
    public String deleteContactById(@PathVariable Integer id, Model model) {
        Contact deletedContact = contactRepository.findById(id).orElse(new Contact());
        contactRepository.delete(deletedContact);

        Iterable<Contact> contactIterable = contactRepository.findAll();
        var contacts = new ArrayList<Contact>();
        for(Contact contact : contactIterable) {
            contacts.add(contact);
        }
        model.addAttribute("contacts", contacts);
        return "redirect:/contacts";
    }

    @PostMapping("/contacts/{id}/del/{phoneid}")
    public String addPhoneNumberById(@PathVariable Integer id,
                                     @PathVariable Integer phoneid,
                                     Model model) {

        PhoneNumber deletedPhoneNumber = phoneNumberRepository.findById(phoneid).orElse(new PhoneNumber());
        phoneNumberRepository.delete(deletedPhoneNumber);

        Contact contact = contactRepository.findById(id).orElse(new Contact());
        model.addAttribute("contact", contact);
        return "redirect:/contacts/" + id.toString();
    }

    @GetMapping("/contacts/{id}")
    public String getContacById(@PathVariable Integer id, Model model) {
        Contact contact = contactRepository.findById(id).orElse(new Contact());
        model.addAttribute("contact", contact);
        return "contact";
    }


//    @GetMapping
//    public  String index() {
//        return "index";
//    }

    @GetMapping("/list")
    public @ResponseBody Iterable<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/list/{id}")
    public @ResponseBody Contact getContactsById(@PathVariable Integer id) {
        return contactRepository.findById(id).orElse(new Contact());
    }

    @GetMapping("/surname")
    public @ResponseBody List<Contact> getContactByName(@RequestParam("surname") String surname) {
        return contactRepository.findBySurname(surname);
    }

    @GetMapping("/surname2")
    public @ResponseBody List<Contact> getContactByName2(@RequestParam("surname") String surname) {
        return contactRepository.findBySurnameStartingWith(surname);
    }

    @GetMapping("/phone-number") //другой способ получить параметры get апроса
    public @ResponseBody List<Contact> getContactByPhoneNumber(HttpServletRequest request) {
        String number = request.getParameter("number");
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
        phonenumber1.setContact(newContact);

        var phonenumber2 = new PhoneNumber();
        phonenumber2.setValue("22222");
        phonenumber2.setType(PhoneType.CELLPHONE);
        phonenumber2.setContact(newContact);

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