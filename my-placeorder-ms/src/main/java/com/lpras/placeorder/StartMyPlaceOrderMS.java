package com.lpras.placeorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class StartMyPlaceOrderMS implements CommandLineRunner {
    static final Logger log = LoggerFactory.getLogger(StartMyPlaceOrderMS.class);

    public static void main(String[] args) {
        SpringApplication.run(StartMyPlaceOrderMS.class, args);
    }

    @Override
    public void run(String... args) {
        //Start-UP Tasks
        log.info("PlaceOrderMS - Launched.... ");
    }

}
