package com.dev.biostoreapi.config;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LocalDateProvider {

   public LocalDate now() {
        return LocalDate.now();
    }
}
