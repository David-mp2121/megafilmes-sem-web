package br.com.davi.megafilmes.model;

import java.time.LocalDate;

public class Episodio {
		
	private String titulo;
	private Integer temporada;
	private Integer numero;
	private Double avaliacao ;
	private LocalDate dataLancamento;
	
	
	public Episodio(Integer numeroTemporada, DadosEpisodio d) {
		this.temporada = numeroTemporada;
		this.titulo = d.titulo();
		this.numero = Integer.valueOf(d.numero());
		this.avaliacao = Double.valueOf(d.avaliacao());
		this.dataLancamento= LocalDate.parse(d.dataLancamento());
		
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getTemporada() {
		return temporada;
	}
	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Double getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
}
