package br.com.davi.megafilmes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.davi.megafilmes.model.DadosSerie;
import br.com.davi.megafilmes.service.ConsumoAPI;
import br.com.davi.megafilmes.service.ConverteDados;

@SpringBootApplication
public class MegafilmesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MegafilmesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Primeiro projeto spring sem web");
		String apikey = "949d261f";
		var consumoApi = new ConsumoAPI();
		
		var endereco = "https://www.omdbapi.com/?t=gilmore+girls&apikey=949d261f";
		
		var json = consumoApi.ObterDados(endereco);
		
		
		System.out.println(json);
		
		var conversos = new ConverteDados();
		
		DadosSerie dados = conversos.obterDados(json, DadosSerie.class);
		System.out.println(dados);

	}

}
