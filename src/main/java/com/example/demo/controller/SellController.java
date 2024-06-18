package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Category;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.model.Account;
import com.example.demo.repository.ItemRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;

@Controller
public class SellController {
	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	Item item;

	@Autowired
	ItemRepository itemRepository;

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
			@RequestParam(name = "image", required = false) MultipartFile image,
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

		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		byte[] bytes = image.getBytes();
		String file = Base64.getEncoder().encodeToString(bytes);
		System.out.println(fileName);

		m.addAttribute("image", image);
		m.addAttribute("name", name);
		m.addAttribute("category", category);
		m.addAttribute("condition", condition);
		m.addAttribute("price", price);
		m.addAttribute("detail", detail);
		return "/sellConfirm";
	}

	@PostMapping("/sell/regist")
	public String store(Model m,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "category", required = false) Category category,
			@RequestParam(name = "condition", required = false) String condition,
			@RequestParam(name = "price", required = false) Integer price,
			@RequestParam(name = "image", required = false) byte[] image,
			@RequestParam(name = "detail", required = false) String detail) {

		Customer customer = account.getId();
		Item item = new Item(category, customer, name, price, image, condition, detail);
		itemRepository.save(item);
		return "/sellFinish";
	}

}
