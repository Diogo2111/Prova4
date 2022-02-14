package br.com.compass.prova.controller.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compass.prova.modelo.Ideologia;
import br.com.compass.prova.modelo.Partido;

public class PartidoDto {

	private long id;
	private String nomePartido;
	private String sigla;
	private Ideologia ideologia;
	private Date dataFundacao;

	public PartidoDto(Partido partido) {
		this.id = partido.getId();
		this.nomePartido = partido.getNomePartido();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.dataFundacao = partido.getDataFundacao();
	}

	public long getId() {
		return id;
	}

	public String getNomePartido() {
		return nomePartido;
	}

	public String getSigla() {
		return sigla;
	}

	public Ideologia getIdeologia() {
		return ideologia;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}
	
	public static List<PartidoDto> converter(List<Partido> estados) {
		
		return estados.stream().map(PartidoDto::new).collect(Collectors.toList());
	}

}
