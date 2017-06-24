package com.jonwelzel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MarsApplication {
    public static void main(String ... args) {
        SpringApplication.run(MarsApplication.class, args);
    }
}
