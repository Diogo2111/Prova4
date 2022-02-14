package br.com.compass.prova.controller.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compass.prova.modelo.Associado;
import br.com.compass.prova.modelo.Cargo;
import br.com.compass.prova.modelo.Sexo;


public class AssociadoDto {

	private long id;
	private String nome;
	private Cargo cargo;
	private Sexo sexo;
	private Date dataNascimento;
	
	public AssociadoDto() {
		
	}
	

	public AssociadoDto(Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
		this.cargo = associado.getCargo();
		this.sexo = associado.getSexo();
		this.dataNascimento = associado.getDataNascimento();
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public static List<AssociadoDto> converter(List<Associado> associados) {
		
		return associados.stream().map(AssociadoDto::new).collect(Collectors.toList());
	}


}
