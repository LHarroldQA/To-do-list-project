package com.qa.todo.rest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todo.DTO.UserDTO;
import com.qa.todo.persistence.domain.User;
import com.qa.todo.persistence.repo.UserRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
    private UserDTO mapToDTO(User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private User testUser;
	private User testUserWithId;
	private UserDTO userDTO;
	private Long id;
	
    @BeforeEach
	void init() {
    	this.repo.deleteAll();
    	
    	this.testUser = new User("Liam","Harrold",21);
    	this.testUserWithId = this.repo.save(this.testUser);
    	this.userDTO = this.mapToDTO(this.testUserWithId);
    	
    	this.id = this.testUserWithId.getId();	
	}
    
    @Test
    void testCreate() throws Exception{
        this.mock
        .perform(request(HttpMethod.POST, "/user/create").contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testUser))
                .accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isCreated())
    			.andExpect(content().json(this.objectMapper.writeValueAsString(userDTO)));
    }

    @Test
    void testRead() throws Exception{
    	this.mock
    			.perform(request(HttpMethod.GET,"/user/read/"+this.id).accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(content().json(this.objectMapper.writeValueAsString(userDTO)));
    }
    
    @Test
    void testReadAll() throws Exception {
    	List<UserDTO> userList = new ArrayList<>();
    	userList.add(this.userDTO);
    	
    	String content = this.mock
    			.perform(request(HttpMethod.GET,"/user/read").accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(userList),content);
    }
    
    @Test
    void testUpdate() throws Exception {
    	UserDTO newUser = new UserDTO(null,"Timmy","Turner",15,null);
    	User updatedUser = new User(newUser.getFirstName(),newUser.getSurname(),newUser.getUserAge());
    	updatedUser.setId(this.id);
    	
        String output = this.mock
                .perform(request(HttpMethod.PUT, "/user/update/" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

        assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedUser)), output);
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/user/delete/" + this.id)).andExpect(status().isNoContent());
    }
}
