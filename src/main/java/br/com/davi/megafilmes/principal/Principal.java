package br.com.davi.megafilmes.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.davi.megafilmes.model.DadosSerie;
import br.com.davi.megafilmes.model.DadosTemporada;
import br.com.davi.megafilmes.service.ConsumoAPI;
import br.com.davi.megafilmes.service.ConverteDados;

public class Principal {

	private Scanner leitura = new Scanner(System.in);
	private ConsumoAPI consumo = new ConsumoAPI();
	private ConverteDados conversor = new ConverteDados();
	private final String ENDERECO = "https://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=949d261f";

	public void exibeMenu() {
		
		System.out.println("Digite o nome da serie para busca");
		
		var nomeSerie = leitura.nextLine().replace(" ", "+");
		
		var json = consumo.ObterDados(ENDERECO + nomeSerie +API_KEY);
		DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
		
		System.out.println(dadosSerie);
		
		
		List<DadosTemporada> ListaDeTemporadas = new ArrayList<>();
		
		 for (int i =1 ; i<=dadosSerie.totalTemporadas(); i++) {
		
			 json = consumo.ObterDados(ENDERECO+nomeSerie+"&season="+i+API_KEY);
			var dadosTemporada = conversor.obterDados(json,DadosTemporada.class);
			
			 ListaDeTemporadas.add(dadosTemporada);
			 
		 }
		ListaDeTemporadas.forEach(System.out::println);
		
		
			
		
	}

}
