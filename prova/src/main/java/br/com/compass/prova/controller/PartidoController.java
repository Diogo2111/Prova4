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

import br.com.compass.prova.controller.dto.PartidoDto;
import br.com.compass.prova.controller.form.AtualizacaoPartidoForm;
import br.com.compass.prova.controller.form.PartidoForm;
import br.com.compass.prova.modelo.Ideologia;
import br.com.compass.prova.modelo.Partido;
import br.com.compass.prova.repository.PartidoRepository;


@RestController
@RequestMapping("/partidos")
public class PartidoController {

	@Autowired
	private PartidoRepository partidoRepository;
	
	@GetMapping
	@Cacheable(value ="listaDeIdeologia")

	public List<PartidoDto> lista(Ideologia ideologia) {
			
			if (ideologia == null) {
				List<Partido> partido = partidoRepository.findAll();
				return PartidoDto.converter(partido);
			}else {
				List<Partido> partido = partidoRepository.findByideologia(ideologia);
				return PartidoDto.converter(partido);
			}
		}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeIdeologia",allEntries = true)
	public ResponseEntity<PartidoDto> cadastrar (@Valid@RequestBody PartidoForm form, UriComponentsBuilder uriBuilder) {
		Partido partido = form.converter(); 
		partidoRepository.save(partido);
		URI uri= uriBuilder.path("/partidos/{id}").buildAndExpand(partido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PartidoDto(partido));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PartidoDto> detalhar(@PathVariable Long id ) {
		Optional<Partido> optional = partidoRepository.findById(id);
		if (optional.isPresent()) {

			partidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeIdeologia",allEntries = true)

	public ResponseEntity<PartidoDto> atualizar(@PathVariable Long id,@Valid@RequestBody AtualizacaoPartidoForm form) {
		Optional<Partido> optional = partidoRepository.findById(id);
		if (optional.isPresent()) {

			partidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else
			return ResponseEntity.notFound().build();
		
	}
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeIdeologia",allEntries = true)

	public ResponseEntity remover (@PathVariable Long id){
		Optional<Partido> optional = partidoRepository.findById(id);
		if (optional.isPresent()) {

			partidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else
			return ResponseEntity.notFound().build();

	}
	
}
