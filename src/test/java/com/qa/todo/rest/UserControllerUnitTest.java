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

import com.qa.todo.DTO.UserDTO;
import com.qa.todo.persistence.domain.User;
import com.qa.todo.service.UserService;

@SpringBootTest
public class UserControllerUnitTest {
	
	@Autowired
	private UserController controller;
	
	@MockBean
	private UserService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
    private UserDTO mapToDTO(User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }
    
    private List<User> userList;
    private User testUser;
    private User testUserWithId;
    private UserDTO userDTO;
    private final Long id = 1L;
    
    @BeforeEach
    void init() {
    	this.userList = new ArrayList<>();
    	this.testUser = new User("Liam","Harrold",21);
    	this.testUserWithId = new User(testUser.getFirstName(),testUser.getSurname(),testUser.getUserAge());
    	this.testUserWithId.setId(this.id);
    	this.userList.add(testUserWithId);
    	this.userDTO = this.mapToDTO(testUserWithId);
    }
    
    @Test
    void createTest() {
        when(this.service.create(testUser)).thenReturn(this.userDTO);
        
        assertThat(new ResponseEntity<UserDTO>(this.userDTO, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testUser));
        
        verify(this.service, times(1)).create(this.testUser);
    }
    
    @Test
    void readTest() {
    	when(this.service.readOne(this.id)).thenReturn(this.userDTO);
    	
    	assertThat(new ResponseEntity<UserDTO>(this.userDTO,HttpStatus.OK))
    		.isEqualTo(this.controller.getUserById(this.id));
    	
    	verify(this.service,times(1)).readOne(this.id);
    }
    
    @Test
    void readAllTest() {
    	when(this.service.readAll())
    		.thenReturn(this.userList
    				.stream()
    				.map(this::mapToDTO)
    				.collect(Collectors.toList()));
    	
    	assertThat(this.controller.getAllUsers().getBody().isEmpty()).isFalse();
    	
    	verify(this.service, times(1)).readAll();
    }
    
    @Test
    void updateTest() {
    	UserDTO newUser = new UserDTO(null,"Timmy","Turner",15,null);
    	UserDTO updatedUser = new UserDTO(this.id,newUser.getFirstName(),newUser.getSurname()
    			,newUser.getUserAge(),newUser.getTasks());
    	
    	when(service.update(newUser, id)).thenReturn(updatedUser);
    	
    	assertThat(new ResponseEntity<UserDTO>(updatedUser,HttpStatus.ACCEPTED))
		.isEqualTo(this.controller.updateUserById(this.id, newUser));
    }
    
    @Test
    void deleteTest() {
    	this.controller.deleteUser(this.id);
    	
    	verify(this.service, times(1)).delete(id);
    }

    

}
