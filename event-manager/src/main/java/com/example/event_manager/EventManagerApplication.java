package com.example.event_manager;

import com.example.event_manager.configuration.SessionFactoryUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
		SessionFactoryUtil.getSessionFactory().openSession();
	}
}
