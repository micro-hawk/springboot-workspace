package com.example.scheduledreminder.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.stereotype.Service;

@Service
public class CalendarOperations {

    public static String convertEpochIntoDate(Long epochTime) {
        Date date = new Date(epochTime);
        DateFormat formatter = new SimpleDateFormat("d MMMM yyyy, HH:mm a (EEEE)", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        return formatter.format(date);
    }
}
