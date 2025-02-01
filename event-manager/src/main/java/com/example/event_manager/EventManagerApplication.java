package com.example.event_manager;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.dao.*;
import com.example.event_manager.entity.*;
import com.example.event_manager.service.EventService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class EventManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
		SessionFactoryUtil.getSessionFactory().openSession();

	}
}
