package com.br.msf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.br.msf")
public class MsfApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsfApplication.class, args);
    }
}