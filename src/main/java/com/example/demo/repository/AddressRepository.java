package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	// SELECT * FROM address WHERE customer_id = ?
	List<Address> findByCustomer_id(Integer customerId);
}
