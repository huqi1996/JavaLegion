package com.huqi.qs.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * @author huqi
 */
public class MainTime {
    public static void main(String[] args) {
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        System.out.println(todayStart);
        System.out.println(todayStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(System.currentTimeMillis());
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        System.out.println(todayEnd);
        System.out.println(todayEnd.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(todayEnd.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - todayStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(3600 * 24 * 1000);
        System.out.println(LocalDateTime.of(2100, 1, 1, 1, 1));
        System.out.println(LocalDateTime.of(2100, 1, 1, 1, 1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
