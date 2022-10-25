package com.vyache.truckemulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TruckEmulatorApplication {

    public static void main(String[] args) {

        SpringApplication.run(TruckEmulatorApplication.class, args);
        //readCSV check = new readCSV();
    }

}
