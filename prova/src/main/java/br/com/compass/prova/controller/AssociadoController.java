package br.com.compass.prova.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compass.prova.controller.dto.AssociadoDto;
import br.com.compass.prova.controller.form.AssociadoForm;
import br.com.compass.prova.controller.form.AtualizacaoAssociadoForm;
import br.com.compass.prova.modelo.Associado;
import br.com.compass.prova.modelo.Cargo;
import br.com.compass.prova.repository.AssociadoRepository;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

	@Autowired
	private AssociadoRepository associadoRepository;

	@GetMapping
	@Cacheable(value ="listaDeCargos")
	public List<AssociadoDto> lista(Cargo cargo) {

		if (cargo == null) {
			List<Associado> associado = associadoRepository.findAll();
			return AssociadoDto.converter(associado);
		} else {
			List<Associado> associado = associadoRepository.findBycargo(cargo);
			return AssociadoDto.converter(associado);
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeCargos",allEntries = true)
	public ResponseEntity<AssociadoDto> cadastrar(@Valid @RequestBody AssociadoForm form,
			UriComponentsBuilder uriBuilder) {
		Associado associado = form.converter(associadoRepository);
		associadoRepository.save(associado);
		URI uri = uriBuilder.path("/associados/{id}").buildAndExpand(associado.getId()).toUri();
		return ResponseEntity.created(uri).body(new AssociadoDto(associado));

	}
	
	
	

	@GetMapping("/{id}")
	public ResponseEntity<AssociadoDto> detalhar(@PathVariable Long id) {
		Optional<Associado> associado = associadoRepository.findById(id);
		if (associado.isPresent()) {
			return ResponseEntity.ok(new AssociadoDto(associado.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCargos",allEntries = true)

	public ResponseEntity<AssociadoDto> atualizar(@PathVariable Long id,
			@Valid @RequestBody AtualizacaoAssociadoForm form) {
		Optional<Associado> optional = associadoRepository.findById(id);
		if (optional.isPresent()) {

			Associado associado = form.atualizar(id, associadoRepository);
			return ResponseEntity.ok(new AssociadoDto(associado));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCargos",allEntries = true)

	public ResponseEntity remover(@PathVariable Long id) {
		Optional<Associado> optional = associadoRepository.findById(id);
		if (optional.isPresent()) {

			associadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else
			return ResponseEntity.notFound().build();

	}

}
