package com.zyxx.common.utils;

import java.time.LocalDate;

public class NumberUtil {

    public static Long getNumber(){
        String replace = LocalDate.now().toString().replace("-", "");
        return Long.valueOf(replace+ Double.valueOf(Math.random() * 9000 + 1000).intValue());
    }

    public static void main(String[] args) {
        System.out.println("fdfdfk="+getNumber());
    }
}
