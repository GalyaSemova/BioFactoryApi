package com.dev.biostoreapi.config;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class LocalDateProvider {

   public LocalDate now() {

       return LocalDate.now();
    }

   public LocalDateTime dateTime() {
       return LocalDateTime.now();
   }
}
