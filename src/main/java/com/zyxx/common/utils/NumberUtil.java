package com.zyxx.common.utils;

import java.time.LocalDate;

public class NumberUtil {

    public static String getNumber(){
        String replace = LocalDate.now().toString().replace("-", "");
        return replace+ Double.valueOf(Math.random() * 9000 + 1000).intValue();
    }

    public static void main(String[] args) {
        System.out.println("fdfdfk="+getNumber());
    }
}
