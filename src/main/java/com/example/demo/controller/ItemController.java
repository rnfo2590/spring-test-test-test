package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import jakarta.servlet.http.HttpSession;

@Controller
public class ItemController {

	@Autowired
	HttpSession session;

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
			@RequestParam(name = "maxPrice", defaultValue = "") String maxPrice,
			@RequestParam(name = "minPrice", defaultValue = "") String minPrice,
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			Model model) {

		Category category = null;
		if (categoryId != null) {
			category = categoryRepository.findById(categoryId).get();
		}
		model.addAttribute("category", category);
		int maxPage = 1;
		model.addAttribute("maxPage", maxPage);
		session.setAttribute("keyword", keyword != null ? keyword : "");
		session.setAttribute("maxPrice", maxPrice != null ? maxPrice : "");
		session.setAttribute("minPrice", minPrice != null ? minPrice : "");
		model.addAttribute("categoryId", categoryId != null ? categoryId : "");
		model.addAttribute("page", page != null ? page : "");
		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);
		System.out.println(maxPrice + " " + minPrice);
		Integer maxprice = null;
		Integer minprice = null;
		List<String> errorList = new ArrayList<>();

		if (maxPrice.length() > 0 && minPrice.length() > 0) {
			if (checkLogic("^[0-9]+$", maxPrice) || checkLogic("^[0-9]+$", minPrice)) {
				errorList.add("・値段は半角数字で入力してください");
			} else if (Integer.parseInt(maxPrice) < Integer.parseInt(minPrice)) {
				errorList.add("・当てはまる金額はありません");
			} else {
				maxprice = Integer.parseInt(maxPrice);
				minprice = Integer.parseInt(minPrice);
			}
		} else if (maxPrice.length() > 0) {
			if (checkLogic("^[0-9]+$", maxPrice)) {
				errorList.add("・値段は半角数字で入力してください");
			} else {
				maxprice = Integer.parseInt(maxPrice);
			}
		} else if (minPrice.length() > 0) {
			if (checkLogic("^[0-9]+$", minPrice)) {
				errorList.add("・値段は半角数字で入力してください");
			} else {
				minprice = Integer.parseInt(minPrice);
			}
		}

		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			return "items";
		}

		// 商品一覧情報の取得
		List<Item> itemList = null;
		if (categoryId == null && keyword == null && maxPrice.length() == 0 && minPrice.length() == 0) {
			itemList = itemRepository.findAll();
		} else if (categoryId == null && keyword == null && maxPrice.length() == 0) {
			// itemsテーブルを最低額を指定して一覧を取得
			itemList = itemRepository.findByPriceGreaterThanEqual(minprice);
		} else if (categoryId == null && keyword == null && minPrice.length() == 0) {
			// itemsテーブルを最高額を指定して一覧を取得
			itemList = itemRepository.findByPriceLessThanEqual(maxprice);
		} else if (categoryId == null && minPrice.length() == 0 && maxPrice.length() == 0) {
			// itemsテーブルをキーワードを指定して一覧を取得
			itemList = itemRepository.findByNameLike("%" + keyword + "%");
		} else if (keyword == null && minPrice.length() == 0 && maxPrice.length() == 0) {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByCategoryId(categoryId);
		} else if (minPrice.length() == 0 && maxPrice.length() == 0) {
			// itemsテーブルをカテゴリーIDとキーワードを指定して一覧を取得
			itemList = itemRepository.findByCategoryIdAndNameLike(categoryId, "%" + keyword + "%");
		} else if (categoryId == null && maxPrice.length() == 0) {
			// itemsテーブルを最低額とキーワードを指定して一覧を取得
			itemList = itemRepository.findByPriceGreaterThanEqualAndNameLike(minprice, "%" + keyword + "%");
		} else if (categoryId == null && minPrice.length() == 0) {
			// itemsテーブルを最高額とキーワードを指定して一覧を取得
			itemList = itemRepository.findByPriceLessThanEqualAndNameLike(maxprice, "%" + keyword + "%");
		} else if (keyword == null && maxPrice.length() == 0) {
			// itemsテーブルを最低額とカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByPriceGreaterThanEqualAndCategoryId(minprice, categoryId);
		} else if (keyword == null && minPrice.length() == 0) {
			// itemsテーブルを最高額とカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByPriceLessThanEqualAndCategoryId(maxprice, categoryId);
		} else if (keyword == null && categoryId == null) {
			// itemsテーブルを最低額と最高額を指定して一覧を取得
			itemList = itemRepository.findByPriceBetween(minprice, maxprice);
		} else if (keyword == null) {
			// itemsテーブルを最低額と最高額とカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByPriceBetweenAndCategoryId(minprice, maxprice, categoryId);
		} else if (categoryId == null) {
			// itemsテーブルを最低額と最高額とキーワードを指定して一覧を取得
			itemList = itemRepository.findByPriceBetweenAndNameLike(minprice, maxprice, "%" + keyword + "%");
		} else if (maxPrice.length() == 0) {
			// itemsテーブルを最低額とカテゴリーIDとキーワードを指定して一覧を取得
			itemList = itemRepository.findByPriceGreaterThanEqualAndCategoryIdAndNameLike(minprice, categoryId,
					"%" + keyword + "%");
		} else if (minPrice.length() == 0) {
			// itemsテーブルを最高額とカテゴリーIDとキーワードを指定して一覧を取得
			itemList = itemRepository.findByPriceLessThanEqualAndCategoryIdAndNameLike(maxprice, categoryId,
					"%" + keyword + "%");
		}

		Cart[] items = new Cart[3];

		if (itemList != null) {
			for (int i = 0; i < itemList.size(); i++) {
				Item item = itemList.get(i);

				if (item.getDeleteFlg() == 1) {
					itemList.remove(i);
					i--;
				}

			}

			for (int i = 0; i < itemList.size(); i++) {
				Item item = itemList.get(i);
				StringBuilder sb = new StringBuilder(item.getName());
				if (item.getName().length() > 10) {
					sb.insert(10, "<br>");
					item.setName(sb.toString());
					itemList.set(i, item);
				} else {
					item.setName(sb.toString() + "<br>");
					itemList.set(i, item);
				}
			}

			maxPage = itemList.size() / 15;
			if (itemList.size() % 15 != 0) {
				maxPage++;
			}
			if (maxPage == 0) {
				maxPage++;
			}
			model.addAttribute("maxPage", maxPage);

			for (int i = 0; i < 3; i++) {
				items[i] = new Cart();
			}
			int count1 = 0;
			int count2 = 0;
			for (int i = 0; i < itemList.size(); i++) {
				if (page == 1) {
					if (i < 15) {
						items[count1].getItems().add(itemList.get(i));
						count2++;
					}
				} else if (page >= 2) {
					if (((page - 1) * 15) - 1 < i && i < page * 15) {
						items[count1].getItems().add(itemList.get(i));
						count2++;

					}
				}
				if (count2 == 5) {
					count2 = 0;
					count1++;
				}
			}
		}

		model.addAttribute("items", items);
		System.out.println(account.getId());
		System.out.println(account.getName());
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
