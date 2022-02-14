package br.com.compass.prova.controller.form;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.compass.prova.modelo.Associado;
import br.com.compass.prova.modelo.Cargo;
import br.com.compass.prova.modelo.Partido;
import br.com.compass.prova.modelo.Sexo;
import br.com.compass.prova.repository.AssociadoRepository;

public class AtualizacaoAssociadoForm {

	private String nome;
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	private Date dataNascimento;
	@Enumerated(EnumType.STRING)
	private Partido partido;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	public Associado atualizar(Long id, AssociadoRepository associadoRepository) {
		Associado associado = associadoRepository.getById(id);
		associado.setNome(this.nome);
		associado.setCargo(this.cargo);
		associado.setSexo(this.sexo);
		associado.setDataNascimento(this.dataNascimento);
		associado.setPartido(this.partido);
		
		return associado;
	}


}
