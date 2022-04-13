package com.example.demo.components;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceClassTest {

    ServiceClass serviceClass;
    Date testDate;

    @BeforeEach
    public void createNewServiceClass() throws ParseException {
        serviceClass = new ServiceClass();
        testDate = (new SimpleDateFormat("yyyy-MM-dd")).parse("1970-01-01");
    }

    @Test
    public void emptyStringShouldFail(){ Assertions.assertFalse(serviceClass.isValidName("")); }
    @Test
    public void nullShouldFail(){ Assertions.assertFalse(serviceClass.isValidName(null)); }
    @Test
    public void digitShouldFail(){ Assertions.assertFalse(serviceClass.isValidName("1")); }
    @Test
    public void charShouldFail(){ Assertions.assertFalse(serviceClass.isValidName("a")); }
    @Test
    public void twoDigitsShouldFail(){ Assertions.assertFalse(serviceClass.isValidName("11")); }
    @Test
    public void stringWithDigitShouldFail(){ Assertions.assertFalse(serviceClass.isValidName("a1")); }
    @Test
    public void longStringShouldFail(){ Assertions.assertFalse(serviceClass.isValidName("aaaaaaaaaaaaaaaaaaaa")); }
    @Test
    public void twoCharStringShouldPass(){ Assertions.assertTrue(serviceClass.isValidName("aa")); }
    @Test
    public void stringShouldPass(){ Assertions.assertTrue(serviceClass.isValidName("aaaa")); }

    @Test
    public void datesShouldBeEqual() throws ParseException { Assertions.assertEquals(testDate, serviceClass.getDateFromDateString("1970-01-01")); }
    @Test
    public void datesShouldNotBeEqual() throws ParseException { Assertions.assertNotEquals(testDate, serviceClass.getDateFromDateString("2222-1-1")); }
    @Test
    public void invalidInputShouldProduceDefaultDate() throws ParseException { Assertions.assertThrows(ParseException.class, () -> serviceClass.getDateFromDateString(" ")); }
    @Test
    public void nullInputShouldProduceDefaultDate() throws ParseException { Assertions.assertEquals(testDate, serviceClass.getDateFromDateString(null)); }
}
