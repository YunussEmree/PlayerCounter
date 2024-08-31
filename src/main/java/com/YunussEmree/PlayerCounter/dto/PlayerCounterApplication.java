package com.YunussEmree.PlayerCounter.dto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class PlayerCounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerCounterApplication.class, args);


		LocalDateTime timeNow = LocalDateTime.now();
		String formattedTime = timeNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(formattedTime);
	}

}
