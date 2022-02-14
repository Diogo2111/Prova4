package br.com.compass.prova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.prova.modelo.Ideologia;
import br.com.compass.prova.modelo.Partido;

public interface PartidoRepository extends JpaRepository <Partido, Long> {


	List<Partido> findBynomePartido(String nomePartido);

	

	List<Partido> findByideologia(Ideologia ideologia);


}
