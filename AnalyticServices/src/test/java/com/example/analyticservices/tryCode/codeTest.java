package com.example.analyticservices.tryCode;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class codeTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.format( DateTimeFormatter.ofPattern("yyyyMMdd") ));

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format( DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS") ));
    }
}
