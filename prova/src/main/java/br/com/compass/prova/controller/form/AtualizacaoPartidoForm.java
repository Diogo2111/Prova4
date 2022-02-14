package br.com.compass.prova.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compass.prova.modelo.Partido;
import br.com.compass.prova.repository.PartidoRepository;

public class AtualizacaoPartidoForm {
	
	
	private String nomePartido;

	private String sigla;
	
	
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
	public Partido atualizar(Long id, PartidoRepository partidoRepository) {
		Partido partido = partidoRepository.getById(id);
		partido.setNomePartido(this.nomePartido);
		partido.setSigla(this.sigla);
		
		return partido;
	}
	
	
	

}
