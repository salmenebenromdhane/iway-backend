package com.bookstore;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
@ComponentScan({"com.bookstore.entity","com.controller","com.services","com.bookstore.dao","com.config"})
public class Application {
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
}
