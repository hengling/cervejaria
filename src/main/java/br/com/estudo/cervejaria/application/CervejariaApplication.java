package br.com.estudo.cervejaria.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"br.com.estudo.cervejaria.*"})
@EnableJpaRepositories(basePackages = {"br.com.estudo.cervejaria.data.repository"})
@EntityScan(basePackages = {"br.com.estudo.cervejaria.data.entity"})
@SpringBootApplication
public class CervejariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CervejariaApplication.class, args);
	}

}
