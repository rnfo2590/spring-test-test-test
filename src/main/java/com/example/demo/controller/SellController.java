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
import com.example.demo.model.Sell;
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
	Sell sell;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CustomerRepository customerRepository;

	private Item item;

	@GetMapping("/sell")
	public String index(Model m) {
		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		m.addAttribute("categories", categoryList);
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
		List<String> errorList = null;
		if ((name == null)
				|| (categoryId == null)
				|| (condition == null)
				|| (price == null)
				|| (detail == null)) {
			errorList = new ArrayList<>();
			errorList.add("必須項目が未入力です");
		}

		if (price != null) {
			if (price < 0) {
				errorList = new ArrayList<>();
				errorList.add("価格は0円以上で入力してください");
			} else if (price > 99999) {
				errorList = new ArrayList<>();
				errorList.add("価格は99999円以下で入力してください");
			}
		}

		if (errorList != null) {
			List<Category> categoryList = categoryRepository.findAll();
			m.addAttribute("categories", categoryList);
			m.addAttribute("errorList", errorList);
			m.addAttribute("name", name);
			m.addAttribute("categoryId", categoryId);
			m.addAttribute("condition", condition);
			m.addAttribute("price", price);
			m.addAttribute("detail", detail);
			return "/sellForm";
		} else {
			String fileName = StringUtils.cleanPath(image.getOriginalFilename());
			byte[] images = image.getBytes();
			item = new Item(categoryRepository.findById(categoryId).get(),
					customerRepository.findById(account.getId()).get(),
					name, price, images, condition, detail, 1);
			String file = Base64.getEncoder().encodeToString(images);
			System.out.println(fileName);
			m.addAttribute("item", item);
			return "/sellConfirm";
		}
	}

	@PostMapping("/sell/regist")
	public String store() {

		itemRepository.save(item);

		return "/sellFinish";
	}

	@GetMapping("/sells")
	public String indexs(Model model) {
		model.addAttribute("sellAll", itemRepository.findById(account.getId()).get());
		return "sells";

	}

	//指定した商品を出品一覧から削除
	@PostMapping("/sell/delete")
	public String deleteCart(@RequestParam("itemId") int itemId) {

		// カート情報から削除
		sell.delete(itemId);
		// 「/sells」にリダイレクト
		return "redirect:/sells";
	}

}
