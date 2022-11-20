package com.zyxx.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateFormatUtil {

    public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static DateTimeFormatter dateTimeFormatter;

    static {
         dateTimeFormatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN);
    }

    public static String parseLocalDateTime(LocalDateTime localDateTime){
        if(!Objects.isNull(localDateTime)){
            return localDateTime.format(dateTimeFormatter).toString();
        }
        return "";
    }


}
