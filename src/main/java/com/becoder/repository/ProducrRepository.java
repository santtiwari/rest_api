package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.model.Product;

public interface ProducrRepository extends JpaRepository<Product, Integer>{

}
