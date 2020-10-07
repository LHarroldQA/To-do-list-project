package com.qa.todo.persistence.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {
	
	@Test
	public void noArgsConstructorTest() {
		Task noArgsTask = new Task();
		Task allArgsTask = new Task(1L,"School","Maths homework", null);
		noArgsTask.setId(1L);
		noArgsTask.setCategory("School");
		noArgsTask.setDescription("Maths homework");
		noArgsTask.setUser(null);
		
		assertEquals(allArgsTask,noArgsTask);
		
	}

}
