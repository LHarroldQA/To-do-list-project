package com.qa.todo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.todo.DTO.UserDTO;
import com.qa.todo.persistence.domain.User;
import com.qa.todo.persistence.repo.UserRepo;

@SpringBootTest
public class UserServiceIntegrationTest {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepo repo;
	
    @MockBean
    private ModelMapper modelMapper;
    
    private UserDTO mapToDTO(User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }
    
    private User testUser;
    private User testUserWithId;
    
    @BeforeEach
    void init() {
    	this.repo.deleteAll();
    	this.testUser = new User("Liam","Harrold",21);
    	this.testUserWithId = this.repo.save(this.testUser);
    }
    
    @Test
    void testCreate() {
        assertThat(this.mapToDTO(this.testUserWithId))
            .isEqualTo(this.service.create(testUser));
    }
    
    @Test
    void testRead() {
        assertThat(this.mapToDTO(this.testUserWithId))
        .isEqualTo(this.service.readOne(this.testUserWithId.getId()));
    }
    
    @Test
    void testReadAll() {
        assertThat(this.service.readAll())
            .isEqualTo(Stream.of(this.mapToDTO(testUserWithId)).collect(Collectors.toList()));
    }
    
    @Test
    void testUpdate() {
    	UserDTO newUser = new UserDTO(null,"Liam","Harrold",21,null);
    	UserDTO updatedUser = new UserDTO(this.testUserWithId.getId(),newUser.getFirstName(),newUser.getSurname(),
    			newUser.getUserAge(),newUser.getTasks());
    	
        assertThat(updatedUser)
        .isEqualTo(this.service.update(newUser, this.testUserWithId.getId()));
    }
    
    @Test
    void testDelete() {
        assertThat(this.service.delete(this.testUserWithId.getId())).isTrue();
    }
}
