package com.lpras.bookstoreweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.lpras.ribbon.MyRibbonConfig;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RibbonClient(name="customRibbonConfig",configuration = MyRibbonConfig.class)
@EnableCircuitBreaker
public class StartMyBookStoreWebMS implements CommandLineRunner {
    static final Logger log = LoggerFactory.getLogger(StartMyBookStoreWebMS.class);

    public static void main(String[] args) {
        SpringApplication.run(StartMyBookStoreWebMS.class, args);
    }

    @Override
    public void run(String... args) {
        //Start-UP Tasks
        log.info(" MyBookStoreWeb - Launched.... ");
    }

}
