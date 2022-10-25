package com.vyache.truckemulator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
/**
 * @author Vyacheslav Kirillov
 * @create 2022.10.25 16:57
 **/
@Slf4j
@Component
public class ReadCSV {
    int num=0;
    Sender sender =new Sender();
    @EventListener(ApplicationReadyEvent.class)
    public void ReadCSV() throws IOException, InterruptedException {

        Reader in = new FileReader("C:\\Users\\games\\Desktop\\MyProjects\\truck-emulator\\src\\main\\resources\\static\\SimpleData.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

        while(true) {
            for (CSVRecord record : records) {
                log.info("\n");
                num++;
                String dateTime = record.get("dateTime");
                String id_truck = record.get("id_truck");
                String lat = record.get("lat");
                String lon = record.get("lon");
                Thread.sleep(1000);
                String message=num + " " + dateTime + " " + id_truck + " " + lat + " " + lon;
                log.info("Read from file "+message);

                sender.sendTE(message);

                //System.out.println(num + " " + dateTime + " " + id_truck + " " + lat + " " + lon);
                //log.info(dateTime);
                //String firstName = record.get("First Name");

            }
            ReadCSV();
        }
    }
}
