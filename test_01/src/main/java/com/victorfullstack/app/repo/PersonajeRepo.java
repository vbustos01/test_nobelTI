package com.victorfullstack.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorfullstack.app.entity.Personaje;

// para hacer las cosas sencillas se hereda desde jpa
public interface PersonajeRepo extends JpaRepository<Personaje, Long>{

}
