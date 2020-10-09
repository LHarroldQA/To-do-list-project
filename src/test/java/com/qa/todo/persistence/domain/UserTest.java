package com.qa.todo.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UserTest {
	
	@Test
	public void noArgsConstructorTest() {
		User noArgsUser = new User();
		User allArgsUser = new User(1L,"Liam","Harrold",21, null);
		noArgsUser.setId(1L);
		noArgsUser.setFirstName("Liam");
		noArgsUser.setSurname("Harrold");
		noArgsUser.setUserAge(21);
		noArgsUser.setTasks(null);
		
		assertEquals(allArgsUser,noArgsUser);
	}
	
	@Test
	public void getIdTest() {
		User user = new User(1L,"Liam","Harrold",21,null);
		Long ID = 1L;
		assertEquals(ID,user.getId());
	}
	
	@Test
	public void setIdTest() {
		User user = new User(1L,"Liam","Harrold",21,null);
		Long newId = 3L;
		user.setId(3L);
		assertEquals(newId,user.getId());
	}
	
	@Test
	public void getFirstNameTest() {
		User user = new User(1L,"Liam","Harrold",21,null);
		assertEquals("Liam",user.getFirstName());
	}
	
	@Test
	public void setFirstNameTest() {
		User user = new User(1L,"Liam","Harrold",21,null);
		user.setFirstName("Dan");
		assertEquals("Dan",user.getFirstName());
	}
	
	@Test
	public void getSurnameTest() {
		User user = new User(1L,"Liam","Harrold",21,null);
		assertEquals("Harrold",user.getSurname());
	}
	
	@Test
	public void setSurnameTest() {
		User user = new User(1L,"Liam","Harrold",21,null);
		user.setSurname("Smith");
		assertEquals("Smith",user.getSurname());
	}
	
	@Test
	public void getAgeTest() {
		User user = new User(1L,"Liam","Harrold",21,null);
		Integer age = 21;
		assertEquals(age,user.getUserAge());
	}
	
	@Test
	public void setAgeTest() {
		User user = new User(1L,"Liam","Harrold",21,null);
		user.setUserAge(55);
		Integer newAge = 55;
		assertEquals(newAge,user.getUserAge());
	}
	
	@Test
	public void getTasksTest() {
		User user = new User(1L,"Liam","Harrold",21,null);
		assertNull(user.getTasks());
	}
	
	@Test
	public void setTasksTest() {
		User user = new User(1L,"Liam","Harrold",21,null);
		Task task = new Task("Shopping","Buy Milk");
		List<Task> list = new ArrayList<>();
		list.add(task);
		user.setTasks(list);
		assertNotNull(user.getTasks());
	}
	
	
}
