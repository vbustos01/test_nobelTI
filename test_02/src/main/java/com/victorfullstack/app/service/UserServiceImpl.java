package com.victorfullstack.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorfullstack.app.entity.User;
import com.victorfullstack.app.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	// que nos inyecte dependencias
	@Autowired
	private UserRepo userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		// utiliza el metodo que heredamos
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		// este metodo realizara la paginacion
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		// 
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {
		// aqui guardamos en la db o actualizamos
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}
