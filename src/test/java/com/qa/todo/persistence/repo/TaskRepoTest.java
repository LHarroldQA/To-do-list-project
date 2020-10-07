package com.qa.todo.persistence.repo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest;

import com.qa.todo.persistence.domain.Task;

@DataLdapTest
public class TaskRepoTest {
	
	@Autowired
	private TaskRepo repo;
	
	private final String TEST_CATEGORY = "School";
	private final String TEST_DESCRIPTION = "Maths homework";
	
	private final Task TEST_TASK = new Task(TEST_CATEGORY,TEST_DESCRIPTION);

	private List<Task> results;
	
	@BeforeEach
	void init() {
		this.repo.deleteAll();
		this.results = new ArrayList<>();
	}
}
