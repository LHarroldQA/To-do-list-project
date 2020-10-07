package com.qa.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todo.DTO.TaskDTO;
import com.qa.todo.exception.TaskNotFoundException;
import com.qa.todo.persistence.domain.Task;
import com.qa.todo.persistence.repo.TaskRepo;
import com.qa.todo.utils.ToDoBeanUtils;

@Service
public class TaskService {
	
	private TaskRepo repo;
	
	private ModelMapper mapper;
	
	private TaskDTO mapToDTO(Task task) {
		return this.mapper.map(task, TaskDTO.class);
	}
	
	@Autowired
	public TaskService(TaskRepo repo,ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper= mapper;
	}
	
	//create
	public TaskDTO create(Task task) {
		Task saved = this.repo.save(task);
		return this.mapToDTO(saved);
	}
	
	//read all
	public List<TaskDTO> readAll(){
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//read one
	public TaskDTO readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(TaskNotFoundException::new));
	}
	
	//update
	public TaskDTO update(TaskDTO taskDTO,Long id) {
		Task toUpdate = this.repo.findById(id).orElseThrow(TaskNotFoundException::new);
		ToDoBeanUtils.mergeObject(taskDTO, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}
	
	//delete
	public boolean delete(Long id) {
		if(!this.repo.existsById(id)) {
			throw new TaskNotFoundException();
		}
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
