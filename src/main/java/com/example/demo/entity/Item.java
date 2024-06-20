package com.example.demo.entity;

import java.sql.Types;
import java.time.LocalDate;
import java.util.Base64;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 商品ID

	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category; // カテゴリーID

	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer; // カスタマーID

	private String name; // 商品名

	private Integer price; // 価格

	@Lob
	//@Type(type = "org.hibernate.type.BinaryType")
	@JdbcTypeCode(Types.VARBINARY)
	@Column(name = "image")
	private byte[] image;

	private String condition;

	private String detail;

	private Integer stock;

	private String creator = "謙汰";

	private LocalDate date_created = LocalDate.now();

	private String updater = "謙汰";

	private LocalDate date_updata = LocalDate.now();

	private Integer ver_num = 0;

	@Column(name = "delete_flg")
	private Integer deleteFlg = 0;
	//	@Transient // 永続化対象外
	//	private Integer quantity; // 数量

	public String getImage() {
		return Base64.getEncoder().encodeToString(image);
	}

	public Item() {

	}

	public Item(Category category, Customer customer, String name, Integer price,
			byte[] image, String condition, String detail, Integer stock) {
		this.category = category;
		this.customer = customer;
		this.name = name;
		this.price = price;
		this.image = image;
		this.condition = condition;
		this.detail = detail;
		this.stock = stock;
	}

}
