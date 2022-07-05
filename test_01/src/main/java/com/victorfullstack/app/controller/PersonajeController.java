package com.victorfullstack.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorfullstack.app.entity.Personaje;
import com.victorfullstack.app.service.PersonajeService;


@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {
	// inyeccion de dependencias
	@Autowired
	private PersonajeService personajeService;
	
	// create
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Personaje personaje){
		return ResponseEntity.status(HttpStatus.CREATED).body(personajeService.save(personaje));
	}
	// Read
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long personajeId){
		// y si el id no existe en la DB?
		Optional<Personaje> oPersonaje = personajeService.findById(personajeId);
		if(!oPersonaje.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oPersonaje);
	}
	// Update
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Personaje personajeDetails, @PathVariable(value = "id") Long personajeId){
		Optional<Personaje> personaje = personajeService.findById(personajeId);
		if(!personaje.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		personaje.get().setName(personajeDetails.getName());
		personaje.get().setStatus(personajeDetails.getStatus());
		personaje.get().setGender(personajeDetails.getGender());
		personaje.get().setImage(personajeDetails.getImage());

		
		return ResponseEntity.status(HttpStatus.CREATED).body(personajeService.save(personaje.get()));
	}
	
	// Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long personajeId){
		// se debe manejar el hecho de que el id puede no existir
		if(!personajeService.findById(personajeId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		personajeService.deleteById(personajeId);
		return ResponseEntity.ok().build();
	}
	
	// Read all users, igual es una peticion get
	@GetMapping
	public List<Personaje> readAll(){
		List<Personaje> personajes = StreamSupport
				.stream(personajeService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return personajes;
	}
}
