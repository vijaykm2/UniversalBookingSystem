package com.ubs.main;

import com.bookingsystem.PersistenceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@Import(PersistenceConfig.class)
@EnableAsync
@EnableScheduling
//@EnableWebSecurity
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
