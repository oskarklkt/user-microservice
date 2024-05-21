package com.griddynamics.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@AllArgsConstructor
@Builder
@SpringBootApplication
public class App {
    @SneakyThrows
    @Generated
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    }
}
