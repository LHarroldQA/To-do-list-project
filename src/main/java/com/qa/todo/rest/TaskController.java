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

import com.qa.todo.DTO.TaskDTO;
import com.qa.todo.persistence.domain.Task;
import com.qa.todo.service.TaskService;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {
	
	private TaskService service;

	@Autowired
	public TaskController(TaskService service) {
		super();
		this.service = service;
	}
	
	//create
	@PostMapping("/create")
	public ResponseEntity<TaskDTO> create(@RequestBody Task task){
		return new ResponseEntity<>(this.service.create(task),HttpStatus.CREATED);
	}
	
	//read all
	@GetMapping("/read")
	public ResponseEntity<List<TaskDTO>> getAllTasks(){
		return ResponseEntity.ok(this.service.readAll());
	}
	
	//read one
	@GetMapping("/read/{id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
		return ResponseEntity.ok(this.service.readOne(id));
	}
	
	//update
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskDTO> updateTaskById(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
		return new ResponseEntity<>(this.service.update(taskDTO, id),HttpStatus.ACCEPTED);
	}
	
	//delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id){
		return this.service.delete(id)? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
