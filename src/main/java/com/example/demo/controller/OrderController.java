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
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam(name = "changeAddress", required = false) String changeAddress,
			Model model) {

		if (changeAddress != null) {
			// お客様情報をまとめる
			Customer customer = new Customer(name, changeAddress, tel, email);
			model.addAttribute("customer", customer);
			System.out.println("変更");
		} else {
			// お客様情報をまとめる
			Customer customer = new Customer(name, address, tel, email);
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

		// セッションスコープのカート情報をクリアする
		cart.clear();

		// 画面返却用注文番号を設定する
		model.addAttribute("orderNumber", order.getId());

		return "ordered";
	}

	// 住所変更画面の表示
	@GetMapping("/order/addaddress")
	public String enter(
			@RequestParam(name = "change", required = false) String change,
			@RequestParam(name = "addAddress", required = false) String addAddress,
			Model model) {
		Customer customer = customerRepository.findById(account.getId()).get();
		model.addAttribute("customer",customer);
		model.addAttribute("change", change);

		if (addAddress != null) {
			Address address = new Address(customer, addAddress);
			addressRepository.save(address);
		}

		// ログインしている顧客IDでアドレステーブルを検索		
		List<Address> addressList = addressRepository.findByCustomer_id(account.getId());
		model.addAttribute("addressList", addressList);
		return "addaddress";
	}
}
