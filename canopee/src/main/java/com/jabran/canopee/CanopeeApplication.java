package com.jabran.canopee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CanopeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanopeeApplication.class, args);
	}

}
