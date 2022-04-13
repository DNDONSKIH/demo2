package com.example.demo.controllers;

import com.example.demo.entities.Contact;
import com.example.demo.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactRestControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ContactRepository repository;

    @BeforeEach
    public void resetDb() {
        repository.deleteAll();
    }

    @AfterEach
    public void checkDb() {
    }

    @Test
    public void newContactShouldBeCreated() throws Exception {
        Contact contact = new Contact(
                "aaaa",
                "bbbb",
                "cccc",
                (new SimpleDateFormat("yyyy-MM-dd")).parse("1970-01-01"));
        mockMvc.perform(
                        post("/addcontact")
                        .content(objectMapper.writeValueAsString(contact))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.surname").value("aaaa"))
                .andExpect(jsonPath("$.middleName").value("bbbb"))
                .andExpect(jsonPath("$.lastName").value("cccc"))
                .andExpect(jsonPath("$.birthday").isNotEmpty());

        List<Contact> queryResult =  repository.findBySurname("aaaa");
        Assertions.assertEquals(1, queryResult.size());
        Contact result = queryResult.get(0);
        Assertions.assertEquals("aaaa", result.getSurname());
        Assertions.assertEquals("bbbb", result.getMiddleName());
        Assertions.assertEquals("cccc", result.getLastName());
    }


}
