package com.qa.todo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.todo.DTO.TaskDTO;
import com.qa.todo.persistence.domain.Task;
import com.qa.todo.service.TaskService;

@SpringBootTest
public class TaskControllerUnitTest {
	
	@Autowired
	private TaskController controller;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@MockBean
	private TaskService service;
	
	private List<Task> taskList;
	private Task testTask;
	private Task testTaskWithId;
	private TaskDTO taskDTO;
	private final Long id = 1L;
	
    private TaskDTO mapToDTO(Task task) {
        return this.modelMapper.map(task, TaskDTO.class);
    }
    
    @BeforeEach
    void init() {
    	this.taskList = new ArrayList<>();
    	this.testTask = new Task("School","Maths homework");
    	this.testTaskWithId = new Task(testTask.getCategory(),testTask.getDescription());
    	this.testTaskWithId.setId(id);
    	this.taskList.add(testTaskWithId);
    	this.taskDTO = this.mapToDTO(testTaskWithId);
    }
    
    @Test
    void createTest() {
        when(this.service.create(testTask)).thenReturn(this.taskDTO);
        
        assertThat(new ResponseEntity<TaskDTO>(this.taskDTO, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testTask));
        
        verify(this.service, times(1)).create(this.testTask);
    }
    
    @Test
    void readTest() {
        when(this.service.readOne(this.id)).thenReturn(this.taskDTO);
        
        assertThat(new ResponseEntity<TaskDTO>(this.taskDTO, HttpStatus.OK))
                .isEqualTo(this.controller.getTaskById(this.id));
        
        verify(this.service, times(1)).readOne(this.id);
    }
    
    @Test
    void readAllTest() {
        when(service.readAll())
            .thenReturn(this.taskList
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList()));
        
        assertThat(this.controller.getAllTasks().getBody()
                .isEmpty()).isFalse();
        
        verify(this.service, times(1)).readAll();
    }
    
    @Test 
    void updateTest() {
    	TaskDTO newTask = new TaskDTO(null,"Shopping","Buy Milk");
    	TaskDTO updatedTask = new TaskDTO(this.id,newTask.getCategory(),newTask.getDescription());
    	
        when(this.service.update(newTask, this.id)).thenReturn(updatedTask);
    
        assertThat(new ResponseEntity<TaskDTO>(updatedTask, HttpStatus.ACCEPTED))
            .isEqualTo(this.controller.updateTaskById(this.id, newTask));
    
        verify(this.service, times(1)).update(newTask, this.id);
    }
    
    @Test
    void deleteTest() {
        this.controller.deleteTask(this.id);

        verify(this.service, times(1)).delete(this.id);
    }

}
