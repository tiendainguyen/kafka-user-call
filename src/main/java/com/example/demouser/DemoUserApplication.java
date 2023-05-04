package com.example.demouser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@SpringBootApplication
public class DemoUserApplication {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	public static void main(String[] args) {
		SpringApplication.run(DemoUserApplication.class, args);
	}
	@KafkaListener(topics = "authen_topic", groupId = "authen_service")
	public void receive(String user) {
    System.out.println("user");
		kafkaTemplate.send("user_topic","this is dai from user service");
	}
}
