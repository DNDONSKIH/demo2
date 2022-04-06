package com.example.demo.components;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


@Component
public class DateStringToDateConverter {

    public Date getDateFromDateString(String dateString){

        String [] dateSubstring = dateString.split("-");
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
        cal.set(Calendar.MONTH, (dateMonthNum-1));
        cal.set(Calendar.DAY_OF_MONTH, dateDayNum);
        return cal.getTime();
    }
}
