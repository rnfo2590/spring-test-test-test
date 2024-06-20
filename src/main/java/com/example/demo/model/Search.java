package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Data
@Component
@SessionScope
public class Search {
	private String keyword;
	private int maxPrice;
	private int minPrice;

	public Search() {

	}

	public Search(String keyword, int maxPrice, int minPrice) {
		this.keyword = keyword;
		this.maxPrice = maxPrice;
		this.minPrice = minPrice;
	}

}
