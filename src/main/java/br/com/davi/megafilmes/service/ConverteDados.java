package br.com.davi.megafilmes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public <T> T obterDados(String json, Class<T> t) {
		try {
			return mapper.readValue(json, t);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());

			throw new RuntimeException(e);
		}

	}

}
