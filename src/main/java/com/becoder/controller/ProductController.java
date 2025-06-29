package com.becoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.dto.ProductDto;
import com.becoder.dto.ProductResponsePageNat;
import com.becoder.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@PostMapping("/save-product")
	public ResponseEntity<?> saveProduct(@ RequestBody ProductDto productDto){
		
		try {
			Boolean saveProduct = productService.saveProduct(productDto);
			if(!saveProduct)
			{
				return new ResponseEntity<>("Product is not saved", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>("saved Product", HttpStatus.CREATED);
	}
	
	@GetMapping("/products")
	public ResponseEntity<?> getProducts(){
		List <ProductDto> allProducts= null;
		try {
			allProducts = productService.getAllProdct();
			if(CollectionUtils.isEmpty(allProducts)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(allProducts,HttpStatus.OK);
		
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProducts(@PathVariable(name = "id") Integer id){
		ProductDto Product= null;
		try {
			Product = productService.getProductById(id);
			if(ObjectUtils.isEmpty(Product)) {
				return new ResponseEntity<>("Product not found with id =" + id, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(Product,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Integer id){
		Boolean deleteProduct= null;
		try {
			deleteProduct = productService.deleteProduct(id);
			if(!deleteProduct) {
				return new ResponseEntity<>("product is not delete ", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(deleteProduct,HttpStatus.OK);
		
	}

	@GetMapping("/page-products")
	public ResponseEntity<?> getProductsPaginate(@RequestParam(name ="pageNo", defaultValue = "0") int pageNo,
			@RequestParam(name="pageSize", defaultValue = "2")int pageSize,
	@RequestParam(name = "sortBy", defaultValue = "id")String sortBy,
	@RequestParam(name = "sortDir", defaultValue = "asc")String sortDir)
	{
		ProductResponsePageNat productResponse = null;
		String name =null;
		name.toUpperCase();
		try {
			
	productResponse = productService.getProductsWithPagenation(pageNo, pageSize, sortBy, sortDir);
			if(ObjectUtils.isEmpty(productResponse)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
		
	}
	
	
}
