package com.example.demo.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceClassTest {

    ServiceClass serviceClass;
    Date testDate;

    @Before
    public void createNewServiceClass() throws ParseException {
        serviceClass = new ServiceClass();
        testDate = (new SimpleDateFormat("yyyy-MM-dd")).parse("1970-01-01");
    }

    @Test
    public void emptyStringShouldFail(){ Assert.assertFalse(serviceClass.isValidName("")); }
    @Test
    public void nullShouldFail(){ Assert.assertFalse(serviceClass.isValidName(null)); }
    @Test
    public void digitShouldFail(){ Assert.assertFalse(serviceClass.isValidName("1")); }
    @Test
    public void charShouldFail(){ Assert.assertFalse(serviceClass.isValidName("a")); }
    @Test
    public void twoDigitsShouldFail(){ Assert.assertFalse(serviceClass.isValidName("11")); }
    @Test
    public void stringWithDigitShouldFail(){ Assert.assertFalse(serviceClass.isValidName("a1")); }
    @Test
    public void longStringShouldFail(){ Assert.assertFalse(serviceClass.isValidName("aaaaaaaaaaaaaaaaaaaa")); }
    @Test
    public void twoCharStringShouldPass(){ Assert.assertTrue(serviceClass.isValidName("aa")); }
    @Test
    public void stringShouldPass(){ Assert.assertTrue(serviceClass.isValidName("aaaa")); }

    @Test
    public void datesShouldBeEqual(){ Assert.assertEquals(testDate, serviceClass.getDateFromDateString("1970-01-01")); }
    @Test
    public void datesShouldNotBeEqual(){ Assert.assertNotEquals(testDate, serviceClass.getDateFromDateString("2222-1-1")); }
    @Test // @Test(expected = ParseException.class)
    public void invalidInputShouldProduceDefaultDate(){ Assert.assertEquals(testDate, serviceClass.getDateFromDateString(" ")); }
    @Test
    public void nullInputShouldProduceDefaultDate(){ Assert.assertEquals(testDate, serviceClass.getDateFromDateString(null)); }
}
