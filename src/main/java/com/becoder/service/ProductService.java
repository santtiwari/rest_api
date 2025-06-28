package com.becoder.service;

import java.util.List;

import com.becoder.dto.ProductDto;
import com.becoder.dto.ProductResponsePageNat;


public interface ProductService {
	
	public Boolean saveProduct(ProductDto productDto);
	
	public List<ProductDto> getAllProdct();
	
	public ProductDto getProductById(Integer id);
	
	public Boolean deleteProduct(Integer id);
	
	public ProductResponsePageNat getProductsWithPagenation(int pageNo, int pageSize, String sortBy, String sortDir); 

}
