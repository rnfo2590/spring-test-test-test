package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SellController {

	@GetMapping("/sell")
	public String index() {

		return "/sellForm";
	}

	@PostMapping("/sell")
	public String create(Model m,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "condition", required = false) String condition,
			@RequestParam(name = "price", required = false) Integer price,
			@RequestParam(name = "image", required = false) String image,
			@RequestParam(name = "detail", required = false) String detail) {

		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if ((name == null)
				|| (category == null)
				|| (condition == null)
				|| (price == null)
				|| (image == null)
				|| (detail == null)) {
			errorList.add("必須項目が未入力です");
		}

		if (errorList.size() > 0) {
			m.addAttribute("errorList", errorList);
			m.addAttribute("name", name);
			m.addAttribute("category", category);
			m.addAttribute("condition", condition);
			m.addAttribute("price", price);
			m.addAttribute("image", image);
			m.addAttribute("detail", detail);
			return "/sellForm";
		}

		return "/";
	}

	@PostMapping("")
	public String store(Model m) {
		return "/";
	}

}
