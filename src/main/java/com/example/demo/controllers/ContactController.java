package com.example.demo.controllers;

import com.example.demo.components.ServiceClass;
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
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ServiceClass serviceClass;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @GetMapping
    public String getAllContacts(Model model) {
        Iterable<Contact> contactIterable = contactRepository.findAll();
        var contacts = new ArrayList<Contact>();
        contactIterable.forEach(contact -> { contacts.add(contact); });
        model.addAttribute("contacts", contacts);
        model.addAttribute("contact", new Contact());
        return "contacts";
    }

    @GetMapping("/{id}")
    public String getContactById(@PathVariable Integer id,  Model model) {
        Contact contact = contactRepository.findById(id).orElse(new Contact());
        model.addAttribute("contact", contact);
        return "contact";
    }

    @GetMapping("/{id}/edit")
    public String editContactById(@PathVariable Integer id, Model model) {
        Contact contact = contactRepository.findById(id).orElse(new Contact());
        model.addAttribute("contact", contact);
        return "contact-edit";
    }

    @GetMapping("/filtered")
    public String getContactByName(HttpServletRequest request, Model model) {
        String key = request.getParameter("key"); /* вместо @RequestParam("key") String key, @RequestParam("findOption") String option,*/
        String option = request.getParameter("findOption");

        List<Contact> contacts = null;
        if(option.equals("phonenumber")) {
            contacts = contactRepository.findByPhoneNumberList_Value(key);
        }
        else {
            contacts = contactRepository.findBySurname(key);
        }
        model.addAttribute("contacts", contacts);
        return "filtered-by";
    }

//    @PostMapping
//    public String addNewContact(@RequestParam("surname") String surname,
//                                @RequestParam("middlename") String middlename,
//                                @RequestParam("lastname") String lastname,
//                                @RequestParam("birthday") String birthday) {
//
//        Date date = serviceClass.getDateFromDateString(birthday);
//        var newContact = new Contact();
//        var numList = new ArrayList<PhoneNumber>();
//        newContact.setSurname(surname);
//        newContact.setMiddleName(middlename);
//        newContact.setLastName(lastname);
//        newContact.setBirthday(date);
//
//        newContact.setPhoneNumberList( numList );
//        contactRepository.save(newContact);
//        return "redirect:/contacts/";
//    }

    @PostMapping
    public String addNewContact(@ModelAttribute("contact") Contact newContact) {
        contactRepository.save(newContact);
        return "redirect:/contacts/";
    }

    @PostMapping("/{id}")
    public String addPhoneNumberById(@PathVariable Integer id,
                                     @RequestParam(name = "value", required = false, defaultValue = "") String requestValue,
                                     @RequestParam(name = "phone-type", required = false, defaultValue = "cellphone") String requestPhoneType) {
        Contact contact = contactRepository.findById(id).orElse(new Contact());

        PhoneType phoneType = switch (requestPhoneType) {
            case "home" -> PhoneType.HOME;
            case "cellphone" -> PhoneType.CELLPHONE;
            default -> PhoneType.WORK;
        };

        var newPhoneNumber = new PhoneNumber();
        newPhoneNumber.setValue(requestValue);
        newPhoneNumber.setType(phoneType);
        newPhoneNumber.setContact(contact);

        var numList = contact.getPhoneNumberList();
        numList.add(newPhoneNumber);
        contact.setPhoneNumberList( numList );
        contactRepository.save(contact);
        return "redirect:/contacts/" + id.toString();
    }

    @DeleteMapping
    public String deleteContactById(@RequestParam("contactId") String contactId) {
        int currentContactId = Integer.parseInt(contactId);
        Contact deletedContact = contactRepository
                .findById(currentContactId).orElse(new Contact());
        contactRepository.delete(deletedContact);
        return "redirect:/contacts";
    }

    @DeleteMapping("/{id}")
    public String deletePhoneNumberById(@RequestParam("phoneId") String phoneId,
                                        @PathVariable Integer id) {
        int currentPhoneId = Integer.parseInt(phoneId);
//        PhoneNumber deletedPhoneNumber = phoneNumberRepository
//                .findById(currentPhoneId).orElse(new PhoneNumber());
//        phoneNumberRepository.delete(deletedPhoneNumber);
        phoneNumberRepository.deleteById(currentPhoneId);
        return "redirect:/contacts/" + id.toString();
    }

    @PatchMapping("/{id}")
    public String deletePhoneNumberById(@RequestParam("surname") String surname,
                                        @RequestParam("middlename") String middlename,
                                        @RequestParam("lastname") String lastname,
                                        @RequestParam("birthday") String birthday,
                                        @PathVariable Integer id) {

        Date date = serviceClass.getDateFromDateString(birthday);
        Contact patchedContact = contactRepository
                .findById(id).orElse(new Contact());

        patchedContact.setSurname(surname);
        patchedContact.setMiddleName(middlename);
        patchedContact.setLastName(lastname);
        patchedContact.setBirthday(date);

        contactRepository.save(patchedContact);

        return "redirect:/contacts/" + id.toString();
    }

}