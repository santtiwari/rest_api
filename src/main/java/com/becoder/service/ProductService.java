package com.becoder.service;

import java.util.List;

import com.becoder.dto.ProductDto;


public interface ProductService {
	
	public Boolean saveProduct(ProductDto productDto);
	
	public List<ProductDto> getAllProdct();
	
	public ProductDto getProductById(Integer id);
	
	public Boolean deleteProduct(Integer id);

}
