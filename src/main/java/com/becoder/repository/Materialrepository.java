package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.model.Material;

public interface Materialrepository extends JpaRepository<Material, Integer> {

}
