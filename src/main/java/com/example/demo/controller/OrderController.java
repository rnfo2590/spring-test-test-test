package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Address;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Controller
public class OrderController {

	@Autowired
	Account account;

	@Autowired
	Cart cart;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	ItemRepository itemRepository;

	// 注文内容確認とお客様情報入力画面を表示
	@GetMapping("/order")
	public String index(Model model) {

		// ログインしている顧客IDで顧客テーブルを検索		
		Customer customer = customerRepository.findById(account.getId()).get();
		model.addAttribute("customer", customer);

		// 注文確認画面に遷移
		return "orderConfirm";
	}

	// 注文内容およびお客様情報内容の確認画面を表示
	@PostMapping("/order/confirm")
	public String confirm(
			@RequestParam(name = "changeAddress", required = false) String changeAddress,
			Model model) {
		Customer customer = customerRepository.findById(account.getId()).get();
		if (changeAddress != null) {
			// お客様情報をまとめる
			customer.setAddress(changeAddress);
			model.addAttribute("customer", customer);
			System.out.println("変更");
		} else {
			model.addAttribute("customer", customer);
		}

		return "orderConfirm";
	}

	// 注文する
	@PostMapping("/order")
	public String order(Model model) {

		//		// 1. お客様情報をDBに格納する
		//		Customer customer = new Customer(name, address, tel, email);
		//		customerRepository.save(customer);

		// 2. 注文情報をDBに格納する
		Order order = new Order(
				account.getId(),
				LocalDate.now(),
				cart.getTotalPrice());
		orderRepository.save(order);

		// 3. 注文詳細情報をDBに格納する
		List<Item> itemList = cart.getItems();
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (Item item : itemList) {
			orderDetails.add(
					new OrderDetail(
							order.getId(),
							item.getId(),
							item.getStock()));
		}
		orderDetailRepository.saveAll(orderDetails);

		for (Item data : cart.getItems()) {
			data.setStock(0);
			itemRepository.save(data);
		}

		// セッションスコープのカート情報をクリアする
		cart.clear();

		// 画面返却用注文番号を設定する
		model.addAttribute("orderNumber", order.getId());

		return "ordered";
	}

	// 住所変更画面の表示
	@GetMapping("/order/addaddress")
	public String change(
			@RequestParam(name = "change", required = false) String change,
			@RequestParam(name = "addAddress", required = false) String addAddress,
			Model model) {
		Customer customer = customerRepository.findById(account.getId()).get();
		model.addAttribute("customer", customer);
		model.addAttribute("change", change);

		// エラーチェック
		List<String> errorList = new ArrayList<>();
			if(addAddress != null && addAddress.equals("")){
				errorList.add("新規住所が未入力です");
				model.addAttribute("change", "erorr");
			}else if(addAddress != null) {
				Address address = new Address(customer, addAddress);
				addressRepository.save(address);
			}

			// エラー発生時は新規登録画面に戻す
			if (errorList.size() > 0) {
				model.addAttribute("errorList", errorList);
			}
		// ログインしている顧客IDでアドレステーブルを検索		
		List<Address> addressList = addressRepository.findByCustomer_id(account.getId());
		Address customerAddress = new Address(customer,customer.getAddress());
		addressList.add(0,customerAddress);
		model.addAttribute("addressList", addressList);
		return "addaddress";
	}
}
