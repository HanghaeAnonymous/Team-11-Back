package com.anonymous.mentalcare.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatChanger {
    public static String dateFormatChange(LocalDateTime ldt){
        return ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
