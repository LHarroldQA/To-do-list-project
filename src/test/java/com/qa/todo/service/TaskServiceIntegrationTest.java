package com.qa.todo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.todo.DTO.TaskDTO;
import com.qa.todo.persistence.domain.Task;
import com.qa.todo.persistence.repo.TaskRepo;

@SpringBootTest
public class TaskServiceIntegrationTest {
	
	@Autowired
	private TaskService service;
	
    @Autowired
    private TaskRepo repo;
	
    @Autowired
    private ModelMapper modelMapper;
    
    private TaskDTO mapToDTO(Task task) {
        return this.modelMapper.map(task, TaskDTO.class);
    }
    
    private Task testTask;
    private Task testTaskWithId;
    
    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.testTask = new Task("School","Maths homework");
        this.testTaskWithId = this.repo.save(this.testTask);
    }
    
    @Test
    void testCreate() {
        assertThat(this.mapToDTO(this.testTaskWithId))
            .isEqualTo(this.service.create(testTask));
    }
    
    @Test
    void testRead() {
        assertThat(this.mapToDTO(this.testTaskWithId))
        .isEqualTo(this.service.readOne(this.testTaskWithId.getId()));
    }
    
    @Test
    void testReadAll() {
        assertThat(this.service.readAll())
            .isEqualTo(Stream.of(this.mapToDTO(testTaskWithId)).collect(Collectors.toList()));
    }
    
    @Test
    void testUpdate(){
    	TaskDTO newTask = new TaskDTO(null,"Shopping","Buy Milk");
    	TaskDTO updatedTask = new TaskDTO(this.testTaskWithId.getId(),newTask.getCategory(),newTask.getDescription());
    	
        assertThat(updatedTask)
        .isEqualTo(this.service.update(newTask, this.testTaskWithId.getId()));
    }
    
    @Test
    void testDelete() {
        assertThat(this.service.delete(this.testTaskWithId.getId())).isTrue();
    }

}
