package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Bittere_Gift
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiDocApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiDocApplication.class, args);
    }
}
