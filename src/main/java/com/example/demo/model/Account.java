package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@Data
@SessionScope
public class Account {

	private Integer id;

	private String name;

	public Account() {
	}

	public Account(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
