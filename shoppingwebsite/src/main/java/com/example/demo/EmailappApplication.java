package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= {"com"})
@EntityScan("com")
@EnableJpaRepositories("com")
public class EmailappApplication {
   public static void main(String[] args) throws Exception {
     ConfigurableApplicationContext ctx= SpringApplication.run(EmailappApplication.class, args);
     
      
   }
   
}
