package br.com.davi.megafilmes.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.davi.megafilmes.model.DadosEpisodio;
import br.com.davi.megafilmes.model.DadosSerie;
import br.com.davi.megafilmes.model.DadosTemporada;
import br.com.davi.megafilmes.model.Episodio;
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

		var json = consumo.ObterDados(ENDERECO + nomeSerie + API_KEY);
		DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);

		System.out.println(dadosSerie);

		List<DadosTemporada> listaDeTemporadas = new ArrayList<>();

		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {

			json = consumo.ObterDados(ENDERECO + nomeSerie + "&season=" + i + API_KEY);
			var dadosTemporada = conversor.obterDados(json, DadosTemporada.class);

			listaDeTemporadas.add(dadosTemporada);

		}

		listaDeTemporadas.forEach(t -> {
			List<DadosEpisodio> episodiosTemporada = t.episodios();
			System.out.println("\n\n Temporada " + t.numero() + "\n");
			episodiosTemporada.forEach(a -> System.out.print(a.numero() + " - " + a.titulo() + " || "));
		});

		List<DadosEpisodio> dadosEpisodios = listaDeTemporadas.stream().flatMap(t -> t.episodios().stream())
				.collect(Collectors.toList());

		System.out.println("\n\nTop 5 episodios : ");
		dadosEpisodios.stream().filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
				.sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()).limit(5)
				.forEach(System.out::println);

		List<Episodio> episodios = listaDeTemporadas.stream()
				.flatMap(t -> t.episodios().stream().map(d -> new Episodio(t.numero(), d)))
				.collect(Collectors.toList());

		// episodios.forEach(System.out::println);
		System.out.println("A partir de que ano voce deseja perquisar episódios ?");
		var ano = leitura.nextInt();
		leitura.nextLine();

		LocalDate dataBusca = LocalDate.of(ano, 1, 1);
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		episodios.stream().filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
				.forEach(e -> System.out.println(
						"Temporada: " + e.getTemporada() + "  Episodio: " + e.getNumero()
						+ "  Titulo: " + e.getTitulo() + "  Data lancamento: " + e.getDataLancamento().format(formatador)

				));

	}

}
