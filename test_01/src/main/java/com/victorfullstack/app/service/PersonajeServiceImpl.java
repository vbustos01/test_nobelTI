package com.victorfullstack.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorfullstack.app.entity.Personaje;
import com.victorfullstack.app.repo.PersonajeRepo;

@Service
public class PersonajeServiceImpl implements PersonajeService{
	//inyeccion de dependencias
	@Autowired
	
	private PersonajeRepo personajeRepository;
	
	// metodo findAll() -> retorna un iterable
	@Override
	@Transactional(readOnly = true)
	public Iterable<Personaje> findAll() {
		// utiliza el metodo que heredamos
		return personajeRepository.findAll();
	}
	// metodo findAll() -> retorna paginable
	@Override
	@Transactional(readOnly = true)
	public Page<Personaje> findAll(Pageable pageable) {
		return personajeRepository.findAll(pageable);
	}
	// metodo findById() -> retorna optional
	@Override
	@Transactional(readOnly = true)
	public Optional<Personaje> findById(Long id) {
		// 
		return personajeRepository.findById(id);
	}
	// metodo save() -> guarda en la db
	@Override
	@Transactional
	public Personaje save(Personaje personaje) {
		return personajeRepository.save(personaje);
	}
	// metodo deleteById para borrar de la db
	@Override
	@Transactional
	public void deleteById(Long id) {
		personajeRepository.deleteById(id);
	}
	
}
