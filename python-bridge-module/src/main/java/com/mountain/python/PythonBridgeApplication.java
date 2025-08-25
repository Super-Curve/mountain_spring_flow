package com.mountain.python;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mountain")
public class PythonBridgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PythonBridgeApplication.class, args);
    }
}