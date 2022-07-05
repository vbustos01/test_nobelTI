package com.victorfullstack.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.victorfullstack.app.entity.Personaje;

public interface PersonajeService {
	public Iterable<Personaje> findAll(); //se puede usar findAll pq lo heredamos de jpaRepository
	public Page<Personaje> findAll(Pageable pageable);
	public Optional<Personaje> findById(Long id);
	public Personaje save(Personaje personaje);
	public void deleteById(Long id);
}
