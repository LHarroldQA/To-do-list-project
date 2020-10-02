package com.qa.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todo.DTO.UserDTO;
import com.qa.todo.exception.UserNotFoundException;
import com.qa.todo.persistence.domain.User;
import com.qa.todo.persistence.repo.UserRepo;
import com.qa.todo.utils.ToDoBeanUtils;

@Service
public class UserService {
	
	private UserRepo repo;
	
	private ModelMapper mapper;
	
	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}
	
	@Autowired
	private UserService(UserRepo repo,ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	//create
	public UserDTO create(User user) {
		User saved = this.repo.save(user);
		return this.mapToDTO(saved);
	}

	//read all
	public List<UserDTO> readAll(){
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//read one
	public UserDTO readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(UserNotFoundException::new));
	}
	
	//update
	public UserDTO update(UserDTO userDTO,Long id) {
		User toUpdate = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
		ToDoBeanUtils.mergeObject(userDTO, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}
	
	//delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
