package com.bookrental.bookrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.*;

@SpringBootApplication
public class BookRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRentalApplication.class, args);

//        for (String s : ZoneId.getAvailableZoneIds()) {
//            System.out.println(s);
//        }
        LocalDate ld = LocalDate.of(2023, Month.DECEMBER,12);

        LocalTime lt = LocalTime.of(14,38);

        LocalDateTime localDateTime = LocalDateTime.of(ld, lt);

        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.of("GMT"));
        System.out.println(zdt);
    }
}
