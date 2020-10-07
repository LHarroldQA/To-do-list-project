package com.qa.todo.persistence.domain;

import static org.junit.Assert.assertEquals;

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
}
