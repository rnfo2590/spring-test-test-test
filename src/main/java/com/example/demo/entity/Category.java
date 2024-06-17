package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // カテゴリーID

	private String name; // カテゴリー名
//
//	private String creator = "謙汰";
//	
//	private LocalDate date_created = LocalDate.now();
//	
//	private String updater = "謙汰";
//	
//	private LocalDate date_updata = LocalDate.now();
//	
//	private Integer ver_num = 0;
//	
//	private Integer delete_flg = 0; 
	// ゲッター

}
