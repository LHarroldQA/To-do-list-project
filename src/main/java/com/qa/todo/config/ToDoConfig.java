package com.qa.todo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ToDoConfig {
	
	@Bean
	@Scope("prototype")
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
