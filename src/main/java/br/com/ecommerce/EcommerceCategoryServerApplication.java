package br.com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EcommerceCategoryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceCategoryServerApplication.class, args);
	}

}
