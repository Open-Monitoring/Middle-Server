package me.soungho.openmonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenMonitoringApplication.class, args);
	}

}
