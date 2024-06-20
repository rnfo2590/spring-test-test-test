package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	Account account;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	// 商品一覧表示
	@GetMapping("/items")
	public String index(
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "maxPrice", required = false) Integer maxPrice,
			@RequestParam(name = "minPrice", required = false) Integer minPrice,
			Model model) {

		Category category = null;
		if (categoryId != null) {
			category = categoryRepository.findById(categoryId).get();
		}
		model.addAttribute("category", category);
		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		List<String> errorList = new ArrayList<>();
		if (maxPrice != null && minPrice != null) {
			if (maxPrice < 0 || minPrice < 0) {
				errorList.add("・値段は0以上で入力してください");
			} else if (maxPrice < minPrice) {
				errorList.add("・当てはまる金額はありません");
			}
		} else if (maxPrice != null) {
			if (maxPrice < 0) {
				errorList.add("・値段は0以上で入力してください");
			}
		} else if (minPrice != null) {
			if (minPrice < 0) {
				errorList.add("・値段は0以上で入力してください");
			}
		}
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			return "items";
		}

		// 商品一覧情報の取得
		List<Item> itemList = null;
		if (categoryId == null && keyword == null && maxPrice == null && minPrice == null) {
			itemList = itemRepository.findAll();
		} else if (categoryId == null && keyword == null && maxPrice == null) {
			// itemsテーブルを最低額を指定して一覧を取得
			itemList = itemRepository.findByPriceGreaterThanEqual(minPrice);
		} else if (categoryId == null && keyword == null && minPrice == null) {
			// itemsテーブルを最高額を指定して一覧を取得
			itemList = itemRepository.findByPriceLessThanEqual(maxPrice);
		} else if (categoryId == null && minPrice == null && maxPrice == null) {
			// itemsテーブルをキーワードを指定して一覧を取得
			itemList = itemRepository.findByNameLike("%" + keyword + "%");
		} else if (keyword == null && minPrice == null && maxPrice == null) {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByCategoryId(categoryId);
		} else if (minPrice == null && maxPrice == null) {
			// itemsテーブルをカテゴリーIDとキーワードを指定して一覧を取得
			itemList = itemRepository.findByCategoryIdAndNameLike(categoryId, "%" + keyword + "%");
		} else if (categoryId == null && maxPrice == null) {
			// itemsテーブルを最低額とキーワードを指定して一覧を取得
			itemList = itemRepository.findByPriceGreaterThanEqualAndNameLike(minPrice, "%" + keyword + "%");
		} else if (categoryId == null && minPrice == null) {
			// itemsテーブルを最高額とキーワードを指定して一覧を取得
			itemList = itemRepository.findByPriceLessThanEqualAndNameLike(maxPrice, "%" + keyword + "%");
		} else if (keyword == null && maxPrice == null) {
			// itemsテーブルを最低額とカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByPriceGreaterThanEqualAndCategoryId(minPrice, categoryId);
		} else if (keyword == null && minPrice == null) {
			// itemsテーブルを最高額とカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByPriceLessThanEqualAndCategoryId(maxPrice, categoryId);
		} else if (keyword == null && categoryId == null) {
			// itemsテーブルを最低額と最高額を指定して一覧を取得
			itemList = itemRepository.findByPriceBetween(minPrice, maxPrice);
		} else if (keyword == null) {
			// itemsテーブルを最低額と最高額とカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByPriceBetweenAndCategoryId(minPrice, maxPrice, categoryId);
		} else if (categoryId == null) {
			// itemsテーブルを最低額と最高額とキーワードを指定して一覧を取得
			itemList = itemRepository.findByPriceBetweenAndNameLike(minPrice, maxPrice, "%" + keyword + "%");
		} else if (maxPrice == null) {
			// itemsテーブルを最低額とカテゴリーIDとキーワードを指定して一覧を取得
			itemList = itemRepository.findByPriceGreaterThanEqualAndCategoryIdAndNameLike(minPrice, categoryId,
					"%" + keyword + "%");
		} else if (minPrice == null) {
			// itemsテーブルを最高額とカテゴリーIDとキーワードを指定して一覧を取得
			itemList = itemRepository.findByPriceLessThanEqualAndCategoryIdAndNameLike(maxPrice, categoryId,
					"%" + keyword + "%");
		}

		int maxList = itemList.size() / 3;
		if (itemList.size() % 3 != 0) {
			maxList++;
		}
		Cart[] items = new Cart[maxList];
		for (int i = 0; i < maxList; i++) {
			items[i] = new Cart();
		}
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < itemList.size(); i++) {
			items[count1].getItems().add(itemList.get(i));
			count2++;
			if (count2 == 3) {
				count2 = 0;
				count1++;
			}
		}

		model.addAttribute("items", items);

		return "items";
	}

	//商品詳細
	@GetMapping("/items/detail")
	public String detail(
			@RequestParam(value = "itemId", defaultValue = "") Integer itemId,
			Model model) {

		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);
		Item item = itemRepository.findById(itemId).get();
		model.addAttribute("item", item);

		return "detail";
	}

}
