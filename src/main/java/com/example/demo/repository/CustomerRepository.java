package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// SELECT * FROM customer WHERE email = ? AND password = ?
	List<Customer> findByEmailAndPassword(String email, String password);

	// SELECT * FROM customer WHERE email = ?
	List<Customer> findByEmail(String email);

	// SELECT * FROM customer WHERE tel = ?
	List<Customer> findByTel(String tel);
}
