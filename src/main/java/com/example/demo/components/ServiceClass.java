package com.example.demo.components;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ServiceClass {

    public Date getDateFromDateString(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = format.parse("1970-01-01");
        if (dateString == null) return parsedDate;
        return format.parse(dateString);
    }

    public boolean isValidName(String name) {
        String regex = "[А-Яа-яA-Za-z]{2,19}$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
        return m.matches();
    }

}
