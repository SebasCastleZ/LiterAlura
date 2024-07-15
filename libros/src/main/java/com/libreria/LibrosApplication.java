package com.libreria;

import com.libreria.repositorio.LibrosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrosApplication  implements CommandLineRunner {

	@Autowired
	private LibrosRepositorio librosRepositorio;
	

	public static void main(String[] args) {
		SpringApplication.run(LibrosApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		//inicializo el repositorio
		Menu menu = new Menu(librosRepositorio);

		//lamo la funcion menu
		menu.menu();
	}
}
