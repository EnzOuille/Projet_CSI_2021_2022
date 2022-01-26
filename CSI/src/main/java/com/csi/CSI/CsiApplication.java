package com.csi.CSI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CsiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsiApplication.class, args);
    }

}
