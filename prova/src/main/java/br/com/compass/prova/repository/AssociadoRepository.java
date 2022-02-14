package br.com.compass.prova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.prova.modelo.Associado;
import br.com.compass.prova.modelo.Cargo;

public interface AssociadoRepository extends JpaRepository <Associado, Long> {

	List<Associado> findByNome(String nome);

	List<Associado> findBycargo(Cargo cargo);

}
