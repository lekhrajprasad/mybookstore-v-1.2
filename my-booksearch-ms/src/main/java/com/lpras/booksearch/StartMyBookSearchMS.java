package com.lpras.booksearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.lpras.ribbon.MyRibbonConfig;

import brave.sampler.Sampler;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients//(basePackages = "com.lpras.booksearch")
@RibbonClient(name="customRibbonConfig", configuration = MyRibbonConfig.class)
//@RibbonClient
public class StartMyBookSearchMS implements CommandLineRunner {
    static final Logger log = LoggerFactory.getLogger(StartMyBookSearchMS.class);

    public static void main(String[] args) {
        SpringApplication.run(StartMyBookSearchMS.class, args);
    }

    @Override
    public void run(String... args) {
        //Start-UP Tasks
        log.info(" StartMyBookSearchMS - Launched.... ");
    }
    @Bean
    public Sampler defaultSampler() {
	return Sampler.ALWAYS_SAMPLE;
    }

}
