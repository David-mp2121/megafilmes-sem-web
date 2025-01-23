package br.com.davi.megafilmes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.davi.megafilmes.model.DadosEpisodio;
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
		
		var endereco = "https://www.omdbapi.com/?t=arrow&apikey=949d261f";
		
		var json = consumoApi.ObterDados(endereco);
		
		
		System.out.println(json);
		
		var conversos = new ConverteDados();
		
		DadosSerie dadosSerie = conversos.obterDados(json, DadosSerie.class);
		
		json = consumoApi.ObterDados("https://www.omdbapi.com/?t=arrow&season=1&episode=1&apikey=949d261f");
		
		System.out.println(json);
		
		
		System.out.println("\n\n"+dadosSerie+"\n\n");

		
		DadosEpisodio dadosEpisodio = conversos.obterDados(json, DadosEpisodio.class);
		
		System.out.println(dadosEpisodio);

	}

}
