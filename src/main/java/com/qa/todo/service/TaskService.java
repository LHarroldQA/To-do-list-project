package com.qa.todo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.todo.persistence.repo.TaskRepo;

@Service
public class TaskService {
	
	private TaskRepo repo;
	
	private ModelMapper mapper;

}
