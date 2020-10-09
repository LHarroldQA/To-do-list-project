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
	
	@Test
	public void getId() {
		Task task = new Task(1L,"Shopping","Buy milk",null);
		Long ID = 1L;
		assertEquals(ID,task.getId());
	}
	
	@Test
	public void setId() {
		Task task = new Task(1L,"Shopping","Buy milk",null);
		task.setId(3L);
		Long ID = 3L;
		assertEquals(ID,task.getId());
	}
	
	@Test
	public void getCategory() {
		Task task = new Task(1L,"Shopping","Buy milk",null);
		assertEquals("Shopping",task.getCategory());
	}
	
	@Test
	public void setCategory() {
		Task task = new Task(1L,"Shopping","Buy milk",null);
		task.setCategory("Making tea");
		assertEquals("Making tea",task.getCategory());
	}
	
	@Test
	public void getDescription() {
		Task task = new Task(1L,"Shopping","Buy milk",null);
		assertEquals("Buy milk",task.getDescription());
	}
	
	@Test
	public void setDescription() {
		Task task = new Task(1L,"Shopping","Buy milk",null);
		task.setDescription("Buy eggs");
		assertEquals("Buy eggs",task.getDescription());
	}
	
}
