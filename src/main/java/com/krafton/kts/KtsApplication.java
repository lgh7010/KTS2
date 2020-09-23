package com.krafton.kts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.krafton"})
public class KtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KtsApplication.class, args);
	}

}
