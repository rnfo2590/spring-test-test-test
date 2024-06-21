package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			@RequestParam(name = "price", required = false) String price,
			@RequestParam(name = "image", required = false) MultipartFile image,
			@RequestParam(name = "detail", required = false) String detail) throws IOException {

		Integer newPrice = null;

		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if ((name == null)
				|| (categoryId == null||categoryId == 0)
				|| (condition.equals("")||condition == null)
				|| (price.equals("")||price == null)
				|| (detail.equals("")||detail == null)) {
			errorList.add("必須項目が未入力です");
		} else if (checkLogic("^[0-9]+$", price)) {
			errorList.add("価格は半角数字のみ入力してください");
		} else {
			newPrice = Integer.parseInt(price);
		}

		if (errorList.size() > 0) {
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
			byte[] images = image.getBytes();
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			item = new Item(categoryRepository.findById(categoryId).get(),
					customerRepository.findById(account.getId()).get(),
					name, newPrice, images, condition, detail.replaceAll("\n", "<br>"), 1);
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
		List<Item> itemList = itemRepository.findByCustomerId(account.getId());

		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);

			if (item.getDeleteFlg() == 1) {
				itemList.remove(i);
				i--;
			}
		}

		model.addAttribute("sellAll", itemList);
		return "sells";

	}

	//指定した商品を出品一覧から削除
	@PostMapping("/sell/delete")
	public String deleteSell(@RequestParam("itemId") int itemId) {

		Item item = itemRepository.findById(itemId).get();
		item.setDeleteFlg(1);
		itemRepository.save(item);

		return "redirect:/sells";
	}

	public static boolean checkLogic(String regex, String target) {
		boolean result = false;
		if (target == null || target.isEmpty())
			return false;
		// 引数に指定した正規表現regexがtargetにマッチするか確認する
		Pattern p1 = Pattern.compile(regex); // 正規表現パターンの読み込み
		Matcher m1 = p1.matcher(target); // パターンと検査対象文字列の照合
		if (m1.matches()) {// 照合結果をtrueかfalseで取得
			result = false;
		} else {
			result = true;
		}
		return result;
	}

}
