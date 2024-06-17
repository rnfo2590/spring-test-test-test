package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // アドレスID

	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer; // カスタマーID
	
	private String address; // 住所

	private String creator = "謙汰";
	
	private LocalDate date_created = LocalDate.now();
	
	private String updater = "謙汰";
	
	private LocalDate date_updata = LocalDate.now();
	
	private Integer ver_num = 0;
	
	private Integer delete_flg = 0; 
	// ゲッター

	public Address() {
		super();
	}
	public Address(Customer customer, String address) {
		super();
		this.customer = customer;
		this.address = address;
	}
	
	
}
