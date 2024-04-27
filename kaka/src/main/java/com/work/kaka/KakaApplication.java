package com.work.kaka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.work.kaka.repository")
public class KakaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaApplication.class, args);
	}

}
