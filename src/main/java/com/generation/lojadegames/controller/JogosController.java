package com.generation.lojadegames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojadegames.model.jogos;
import com.generation.lojadegames.repository.JogosRepository;
import com.generation.lojadegames.repository.TemaRepository;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/Jogos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JogosController {
	
	@Autowired
	private JogosRepository jogosRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity <List<jogos>> getAll(){
		return ResponseEntity.ok(jogosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<jogos> getById(@PathVariable Long id){
		return jogosRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List<jogos>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(jogosRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping 
	public ResponseEntity<jogos> post(@Valid @RequestBody jogos Jogos){
		if (temaRepository.existsById(Jogos.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(jogosRepository.save(Jogos));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
	}
	
	@PutMapping
	public ResponseEntity <jogos> put(@Valid @RequestBody jogos Jogos){
		if (jogosRepository.existsById(Jogos.getTema().getId())) {
			
			if (temaRepository.existsById(Jogos.getTema().getId()))
				return ResponseEntity.status(HttpStatus.OK)
					.body(jogosRepository.save(Jogos)); 
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
	}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		Optional<jogos> postagem = jogosRepository.findById(id);
		
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		jogosRepository.deleteById(id);
		
	}
}
