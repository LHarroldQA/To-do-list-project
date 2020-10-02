package com.qa.todo.rest;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.qa.todo.DTO.UserDTO;
import com.qa.todo.persistence.domain.User;
import com.qa.todo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	private UserService service;

	@Autowired
	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
	//create
	@PostMapping("/create")
	public ResponseEntity<UserDTO> create(@RequestBody User user){
		return new ResponseEntity<>(this.service.create(user),HttpStatus.CREATED);
	}
	
	//read all
	@GetMapping("/read")
	public ResponseEntity<List<UserDTO>> getAllBands(){
		return ResponseEntity.ok(this.service.readAll());
	}
	
	//read one
	@GetMapping("/read/{id}")
	public ResponseEntity<UserDTO> getBandById(@PathVariable Long id){
		return ResponseEntity.ok(this.service.readOne(id));
	}
	
	//update
	@PutMapping("/update/{id}")
	ResponseEntity<UserDTO> updateBandById(@PathVariable Long id,@RequestBody UserDTO userDTO){
		return new ResponseEntity<>(this.service.update(userDTO, id),HttpStatus.ACCEPTED);
	}
	
	//delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserDTO> deleteGuitarist(@PathVariable Long id){
		return this.service.delete(id)? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
