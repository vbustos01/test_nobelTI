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

import com.victorfullstack.app.entity.User;
import com.victorfullstack.app.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
	// realizamos nuevamente una inyeccion de dependencias
	@Autowired
	private UserService userService;
	
	// create, esta operacion corresponde a una de tipo POST en REST
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	// Read, esta sera una operacion de tipo GET
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long userId){
		// y si el id no existe en la DB?
		Optional<User> oUser = userService.findById(userId);
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUser);
	}
	
	// Update
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value = "id") Long userId){
		Optional<User> user = userService.findById(userId);
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		// para copiar un objeto entero se puede usar beanUtils
		//BeanUtils.copyProperties(userDetails, user.get());
		
		
		// aqui se copia metodo por metodo
		user.get().setName(userDetails.getName());
		user.get().setLastName(userDetails.getLastName());
		user.get().setAge(userDetails.getAge());
		user.get().setGender(userDetails.getGender());
		user.get().setEmail(userDetails.getEmail());

		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
	}
	
	// Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId){
		// se debe manejar el hecho de que el id puede no existir
		if(!userService.findById(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteById(userId);
		return ResponseEntity.ok().build();
	}
	
	// Read all users, igual es una peticion get
	@GetMapping
	public List<User> readAll(){
		List<User> users = StreamSupport
				.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return users;
	}
}
