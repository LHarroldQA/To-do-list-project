package com.qa.todo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todo.persistence.repo.TaskRepo;

@Service
public class TaskService {
	
	private TaskRepo repo;
	
	private ModelMapper mapper;
	
	@Autowired
	public TaskService(TaskRepo repo,ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper= mapper;
	}
	

}
