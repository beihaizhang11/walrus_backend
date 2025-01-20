package com.walrus.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.walrus.pojo.entity"})
@EnableJpaRepositories(basePackages = {"com.walrus.server.repository"})
public class WalrusApplication {
    public static void main(String[] args) {
        SpringApplication.run(WalrusApplication.class, args);
    }
} 