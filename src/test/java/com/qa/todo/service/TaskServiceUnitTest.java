package com.qa.todo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.todo.DTO.TaskDTO;
import com.qa.todo.persistence.domain.Task;
import com.qa.todo.persistence.repo.TaskRepo;

@SpringBootTest
public class TaskServiceUnitTest {
	
	@Autowired
	private TaskService service;
	
	@MockBean
	private TaskRepo repo;
	
    @MockBean
    private ModelMapper modelMapper;
	
    private TaskDTO mapToDTO(Task task) {
        return this.modelMapper.map(task, TaskDTO.class);
    }
    
	private List<Task> taskList;
	private Task testTask;
	private Task testTaskWithId;
	private TaskDTO taskDTO;
	
	final Long id = 1L;
	
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
        when(this.repo.save(testTask)).thenReturn(testTaskWithId);

        when(this.modelMapper.map(testTaskWithId, TaskDTO.class)).thenReturn(taskDTO);
        
        TaskDTO expected = this.taskDTO;
        TaskDTO actual = this.service.create(testTask);

        assertThat(expected).isEqualTo(actual);

        verify(this.repo, times(1)).save(this.testTask);
    }
    
    @Test
    void readTest() {
        when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testTaskWithId));

        when(this.modelMapper.map(testTaskWithId, TaskDTO.class)).thenReturn(taskDTO);

        assertThat(this.taskDTO).isEqualTo(this.service.readOne(this.id));

        verify(this.repo, times(1)).findById(this.id);
    }
    
    @Test
    void readAllTest() {
        when(repo.findAll()).thenReturn(this.taskList);

        when(this.modelMapper.map(testTaskWithId, TaskDTO.class)).thenReturn(taskDTO);

        assertThat(this.service.readAll().isEmpty()).isFalse();

        verify(repo, times(1)).findAll();
    }
    
    @Test
    void updateTest() {
    	final Long ID = 1L;
    	
    	TaskDTO newTask = new TaskDTO(null,"Shopping","Buy milk");
    	
    	Task task = new Task("Shopping","Buy milk");
    	task.setId(ID);
    	
    	Task updatedTask = new Task(newTask.getCategory(),newTask.getDescription());
    	updatedTask.setId(ID);
    	
    	TaskDTO updatedDTO = new TaskDTO(ID,updatedTask.getCategory(),updatedTask.getDescription());
    
        when(this.repo.findById(this.id)).thenReturn(Optional.of(task));

        when(this.repo.save(updatedTask)).thenReturn(updatedTask);

        when(this.modelMapper.map(updatedTask, TaskDTO.class)).thenReturn(updatedDTO);

        assertThat(updatedDTO).isEqualTo(this.service.update(newTask, this.id));

        verify(this.repo, times(1)).findById(1L);

        verify(this.repo, times(1)).save(updatedTask);
    }
    
    @Test
    void deleteTest() {
        when(this.repo.existsById(id)).thenReturn(true, false);

        assertThat(this.service.delete(id)).isTrue();

        verify(this.repo, times(1)).deleteById(id);

        verify(this.repo, times(2)).existsById(id);
    }

}
