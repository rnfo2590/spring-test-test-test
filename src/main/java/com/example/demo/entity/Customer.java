package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 顧客ID

	private String name; // 名前

	private String address; // 住所

	private String tel; // 電話番号

	private String email; // メールアドレス

	private String password; // パスワード

	private String creator = "謙汰";
	
	private LocalDate date_created = LocalDate.now();
	
	private String updater = "謙汰";
	
	private LocalDate date_updata = LocalDate.now();
	
	private Integer ver_num = 0;
	
	private Integer delete_flg = 0; 
	
	// コストラクタ
	public Customer() {
	}

	public Customer(String name, String address, String tel, String email) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
	}

	public Customer(String name, String address, String tel, String email, String password) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.password = password;
	}

	// ゲッター

}
