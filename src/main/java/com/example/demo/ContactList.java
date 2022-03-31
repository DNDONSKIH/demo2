package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hibernate.Session;
import java.util.List;

@Component
public class ContactList {
//    @Autowired
//    HibernateSessionCreator sessionCreator;
//
//    public List<Contact> getAllContacts() {
//        try (Session session = sessionCreator.getSessionFactory().openSession()) {
//            String hql = String.format("From %s", Contact.class.getSimpleName());
//            List<Contact> result = session.createQuery(hql).getResultList();
//            return result;
//        }
//    }
//
//    public Contact getContactByID(Integer id) {
//        try (Session session = sessionCreator.getSessionFactory().openSession()) {
//            Contact contact = session.get(Contact.class, id);
//            return contact;
//        }
//    }
//
//    public void AddContact(Contact newContact) {
//        try (Session session = sessionCreator.getSessionFactory().openSession()) {
//            session.beginTransaction();
//
//            session.save(newContact);
//
//            session.getTransaction().commit();
//        }
//    }
//
//    public void UpdateContact(Integer id, Contact newContact) {
//        try (Session session = sessionCreator.getSessionFactory().openSession()) {
//            session.beginTransaction();
//
//            Contact contact = session.get(Contact.class, id);
//            contact.setSurname(newContact.getSurname());
//            contact.setMiddleName(newContact.getMiddleName());
//            contact.setLastName(newContact.getLastName());
//            contact.setBirthday(newContact.getBirthday());
//            contact.setPhoneNumberList(newContact.getPhoneNumberList());
//            session.update(contact);
//
//            session.getTransaction().commit();
//        }
//    }
//
//    public void DeleteContact(Integer id) {
//        try (Session session = sessionCreator.getSessionFactory().openSession()) {
//            session.beginTransaction();
//
//            Contact contact = session.get(Contact.class, id);
//            session.delete(contact);
//
//            session.getTransaction().commit();
//        }
//    }

}