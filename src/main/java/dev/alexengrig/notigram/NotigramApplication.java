package dev.alexengrig.notigram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class NotigramApplication {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(NotigramApplication.class, args);
    }
}
