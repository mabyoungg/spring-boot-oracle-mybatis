package org.example.springbootoraclemybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootOracleMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOracleMybatisApplication.class, args);
    }

}
