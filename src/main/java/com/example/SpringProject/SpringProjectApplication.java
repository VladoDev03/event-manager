package com.example.SpringProject;

//import com.example.SpringProject.User.UserService;
import com.example.SpringProject.configuration.SessionFactoryUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
		SessionFactoryUtil.getSessionFactory().openSession();

	}

}
