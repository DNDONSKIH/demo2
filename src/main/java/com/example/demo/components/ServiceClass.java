package com.example.demo.components;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ServiceClass {

    public Date getDateFromDateString(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = new Date();
        try {
            parsedDate = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    public boolean isValidName(String name) {
//        String regex = "^[A-Za-z]\\w{5,29}$";
        String regex = "[А-Яа-яA-Za-z]{2,19}$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        String phone = phoneNumber.replaceAll("[\\D]","");
        if ((phone.length() >= 9) && (phone.length()<=11))
            return true;
        return false;
    }

}
