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
import java.util.*;

@Controller
@RequestMapping("/contacts")
public class ContactController {

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
        return "contacts";
    }

    @GetMapping("/{id}")
    public String getContactById(@PathVariable Integer id, Model model) {
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


    @GetMapping("/filtered-by-surname")
    public String getContactByName(@RequestParam("surname") String surname, Model model) {
        List<Contact> contacts = contactRepository.findBySurname(surname);
        model.addAttribute("contacts", contacts);
        return "filtered-by-surname";
    }

    @GetMapping("/filtered-by-phone-number")
    public String getContactByPhoneNumber(@RequestParam("phonenumber") String phoneNumber, Model model) {
        List<Contact> contacts = contactRepository.findByPhoneNumberList_Value(phoneNumber);
        model.addAttribute("contacts", contacts);
        return "filtered-by-phone-number";
    }

    @PostMapping
    public String addNewContact(@RequestParam("surname") String surname,
                                @RequestParam("middlename") String middlename,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("birthday") String birthday) {

        String [] dateSubstring = birthday.split("-");
        int dateYearNum = 2000;
        int dateMonthNum = 1;
        int dateDayNum = 1;
        try {
            dateYearNum = Integer.parseInt(dateSubstring[0]);
            dateMonthNum = Integer.parseInt(dateSubstring[1]);
            dateDayNum = Integer.parseInt(dateSubstring[2]);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

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
        PhoneNumber deletedPhoneNumber = phoneNumberRepository
                .findById(currentPhoneId).orElse(new PhoneNumber());
        phoneNumberRepository.delete(deletedPhoneNumber);
        return "redirect:/contacts/" + id.toString();
    }

    @PatchMapping("/{id}")
    public String deletePhoneNumberById(@RequestParam("surname") String surname,
                                        @RequestParam("middlename") String middlename,
                                        @RequestParam("lastname") String lastname,
                                        @RequestParam("birthday") String birthday,
                                        @PathVariable Integer id) {

        String [] dateSubstring = birthday.split("-");
        int dateYearNum = 2000;
        int dateMonthNum = 1;
        int dateDayNum = 1;
        try {
            dateYearNum = Integer.parseInt(dateSubstring[0]);
            dateMonthNum = Integer.parseInt(dateSubstring[1]);
            dateDayNum = Integer.parseInt(dateSubstring[2]);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, dateYearNum);
        cal.set(Calendar.MONTH, dateMonthNum);
        cal.set(Calendar.DAY_OF_MONTH, dateDayNum);
        Date date = cal.getTime();

        Contact patchedContact = contactRepository
                .findById(id).orElse(new Contact());

        patchedContact.setSurname(surname);
        patchedContact.setMiddleName(middlename);
        patchedContact.setLastName(lastname);
        patchedContact.setBirthday(date);

        contactRepository.save(patchedContact);

        return "redirect:/contacts/" + id.toString();
    }


//    @GetMapping("/find-by-phone-number") //другой способ получить параметры get апроса
//    public @ResponseBody List<Contact> getContactByPhoneNumber(HttpServletRequest request) {
//        String number = request.getParameter("number");
//        return contactRepository.findByPhoneNumberList_Value(number);
//    }
}