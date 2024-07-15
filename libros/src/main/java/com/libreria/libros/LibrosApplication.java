package com.libreria.libros;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LibrosApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LibrosApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu();
		menu.menu();
	}
}
