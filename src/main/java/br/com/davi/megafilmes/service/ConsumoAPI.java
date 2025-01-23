package br.com.davi.megafilmes.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

	public String ObterDados(String endereco) {
		
		try {
			URI uri = new URI(endereco);

			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder(uri).build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			return response.body();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		
	}

}
