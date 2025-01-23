package br.com.davi.megafilmes.service;

public interface IConverteDados {

	<T> T	obterDados(String json,Class<T> t);
}
