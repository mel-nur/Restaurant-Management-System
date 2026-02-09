package com.melnur.AdisyonTakipSistemi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan
public class AdisyonTakipSistemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdisyonTakipSistemiApplication.class, args);
	}

}
