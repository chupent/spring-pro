package com.wx.cp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppSpringbootwxApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSpringbootwxApplication.class, args);
    }

}
