package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ItemRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SellController {
	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CustomerRepository customerRepository;

	private Item item;

	@GetMapping("/sell")
	public String index(Model model) {
		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);
		return "/sellForm";
	}

	@PostMapping("/sell")
	public String create(Model m,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "condition", required = false) String condition,
			@RequestParam(name = "price", required = false) Integer price,
			@RequestParam(name = "image", required = false) MultipartFile image,
			@RequestParam(name = "detail", required = false) String detail) throws IOException {

		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if ((name == null)
				|| (categoryId == null)
				|| (condition == null)
				|| (image == null)
				|| (price == null)
				|| (detail == null)) {
			errorList.add("必須項目が未入力です");
			m.addAttribute("errorList", errorList);
			m.addAttribute("name", name);
			m.addAttribute("categoryId", categoryId);
			m.addAttribute("condition", condition);
			m.addAttribute("price", price);
			m.addAttribute("image", image);
			m.addAttribute("detail", detail);
			return "/sellForm";
		}

		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		byte[] images = image.getBytes();
		item = new Item(categoryRepository.findById(categoryId).get(),
				//new Customer(),
				customerRepository.findById(account.getId()).get(),
				name, price, images, condition, detail, 1);
		//item.setImage(images);
		String file = Base64.getEncoder().encodeToString(images);
		System.out.println(fileName);

		m.addAttribute("item", item);

		return "/sellConfirm";
	}

	@PostMapping("/sell/regist")
	public String store() {

		itemRepository.save(item);

		return "/sellFinish";
	}

}
