package br.com.compass.prova.controller.form;

import java.util.Date;

import br.com.compass.prova.modelo.Ideologia;
import br.com.compass.prova.modelo.Partido;

public class PartidoForm {
	
	private String nomePartido;
	
	private String sigla;
	
	private Ideologia ideologia;

	private Date dataFundacao;

	public String getNomePartido() {
		return nomePartido;
	}

	public void setNomePartido(String nomePartido) {
		this.nomePartido = nomePartido;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Ideologia getIdeologia() {
		return ideologia;
	}

	public void setIdeologia(Ideologia ideologia) {
		this.ideologia = ideologia;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public Partido converter() {
		return new Partido(nomePartido, sigla, ideologia, dataFundacao);
	}
}
