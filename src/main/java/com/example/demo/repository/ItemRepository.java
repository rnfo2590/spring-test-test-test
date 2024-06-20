package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	// SELECT * FROM items WHERE category_id = ?
	List<Item> findByCategoryId(Integer categoryId);

	// SELECT * FROM items WHERE price >= ?
	List<Item> findByPriceGreaterThanEqual(Integer minPrice);

	// SELECT * FROM items WHERE price <= ?
	List<Item> findByPriceLessThanEqual(Integer maxPrice);

	// SELECT * FROM items WHERE name LIKE ?
	List<Item> findByNameLike(String keyword);

	// SELECT * FROM items WHERE category_id = ? AND name LIKE ?
	List<Item> findByCategoryIdAndNameLike(Integer categoryId, String keyword);

	// SELECT * FROM items WHERE price >= ? AND name LIKE ?
	List<Item> findByPriceGreaterThanEqualAndNameLike(Integer minPrice, String keyword);

	// SELECT * FROM items WHERE price <= ? AND name LIKE ?
	List<Item> findByPriceLessThanEqualAndNameLike(Integer maxPrice, String keyword);

	// SELECT * FROM items WHERE price >= ? AND category_id = ?
	List<Item> findByPriceGreaterThanEqualAndCategoryId(Integer minPrice, Integer categoryId);

	// SELECT * FROM items WHERE price <= ? AND category_id = ?
	List<Item> findByPriceLessThanEqualAndCategoryId(Integer maxPrice, Integer categoryId);

	// SELECT * FROM items WHERE price Between ? AND ?
	List<Item> findByPriceBetween(Integer minPrice, Integer maxPrice);

	// SELECT * FROM items WHERE price Between ? AND ? AND category_id = ?
	List<Item> findByPriceBetweenAndCategoryId(Integer minPrice, Integer maxPrice, Integer categoryId);

	// SELECT * FROM items WHERE price Between ? AND ? AND name LIKE ?
	List<Item> findByPriceBetweenAndNameLike(Integer minPrice, Integer maxPrice, String keyword);

	// SELECT * FROM items WHERE price >= ? AND category_id = ? AND name LIKE ?
	List<Item> findByPriceGreaterThanEqualAndCategoryIdAndNameLike(Integer minPrice, Integer categoryId,
			String keyword);

	// SELECT * FROM items WHERE price <= ? AND category_id = ? AND name LIKE ?
	List<Item> findByPriceLessThanEqualAndCategoryIdAndNameLike(Integer maxPrice, Integer categoryId, String keyword);

	//SELECT + FROM items WHERE customer_id = ?
	List<Item> findByCustomerId(Integer customerId);

	//SELECT + FROM items WHERE customer_id = ? AND delete_flg = ?
	List<Item> findByCustomerIdANDDeleteFlg(Integer customerId, Integer delete_flg);

}
