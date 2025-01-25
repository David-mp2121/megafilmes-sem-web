package br.com.davi.megafilmes;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.davi.megafilmes.principal.Principal;

@SpringBootApplication
public class MegafilmesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MegafilmesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();

		principal.exibeMenu();

	}

}
