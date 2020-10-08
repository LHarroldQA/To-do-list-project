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

import com.qa.todo.DTO.UserDTO;
import com.qa.todo.persistence.domain.User;
import com.qa.todo.persistence.repo.UserRepo;

@SpringBootTest
public class UserServiceUnitTest {
	
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepo repo;
	
    @MockBean
    private ModelMapper modelMapper;

    private UserDTO mapToDTO(User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }
    
    private List<User> userList;
    private User testUser;
    private User testUserWithId;
    private UserDTO userDTO;
    
    final Long id = 1L;
    
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
        when(this.repo.save(testUser)).thenReturn(testUserWithId);

        when(this.modelMapper.map(testUserWithId, UserDTO.class)).thenReturn(userDTO);
        
        UserDTO expected = this.userDTO;
        UserDTO actual = this.service.create(testUser);

        assertThat(expected).isEqualTo(actual);

        verify(this.repo, times(1)).save(this.testUser);
    }
    
    @Test
    void readTest() {
        when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testUserWithId));

        when(this.modelMapper.map(testUserWithId, UserDTO.class)).thenReturn(userDTO);

        assertThat(this.userDTO).isEqualTo(this.service.readOne(this.id));

        verify(this.repo, times(1)).findById(this.id);
    }
    
    @Test
    void readAllTest() {
        when(repo.findAll()).thenReturn(this.userList);

        when(this.modelMapper.map(testUserWithId, UserDTO.class)).thenReturn(userDTO);

        assertThat(this.service.readAll().isEmpty()).isFalse();

        verify(repo, times(1)).findAll();
    }
    
    @Test
    void updateTest() {
    	final Long ID = 1L;
    	
    	UserDTO newUser = new UserDTO(null,"Timmy","Turner",15,null);
    	
    	User user = new User("Timmy","Turner",15);
    	user.setId(ID);
    	
    	User updatedUser = new User(user.getFirstName(),user.getSurname(),user.getUserAge());
    	updatedUser.setId(ID);
    	
    	UserDTO updatedDTO = new UserDTO(ID,newUser.getFirstName(),newUser.getSurname()
    			,newUser.getUserAge(),newUser.getTasks());
    	
        when(this.repo.findById(this.id)).thenReturn(Optional.of(user));

        when(this.repo.save(updatedUser)).thenReturn(updatedUser);

        when(this.modelMapper.map(updatedUser, UserDTO.class)).thenReturn(updatedDTO);

        assertThat(updatedDTO).isEqualTo(this.service.update(newUser, this.id));

        verify(this.repo, times(1)).findById(1L);

        verify(this.repo, times(1)).save(updatedUser);
    }
    
    @Test
    void deleteTest() {
        when(this.repo.existsById(id)).thenReturn(true, false);

        assertThat(this.service.delete(id)).isTrue();

        verify(this.repo, times(1)).deleteById(id);

        verify(this.repo, times(2)).existsById(id);
    }
}
