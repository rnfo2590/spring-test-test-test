package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Item;

@Component
@SessionScope
public class Sell {

	// 出品リスト
	private List<Item> items = new ArrayList<>();

	// 出品リストゲッター
	public List<Item> getItems() {

		return items;
	}

	// 出品した商品を削除
	public void delete(int itemId) {

		// 現在のカートの商品から同一IDの商品を探す
		for (Item item : items) {
			// 対象の商品IDが見つかった場合削除する
			if (item.getId() == itemId) {
				items.remove(item);
				break;
			}
		}
	}
}
