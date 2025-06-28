package com.becoder.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.becoder.dto.ProductDto;
import com.becoder.model.Product;
import com.becoder.repository.ProducrRepository;
import com.becoder.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProducrRepository productRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Boolean saveProduct(ProductDto productDto) {
		
		/*
		 * Product product= new Product(); product.setId(productDto.getId());
		 * product.setName(productDto.getName());
		 * product.setPrice(productDto.getPrice());
		 * product.setDescription(productDto.getDescription());
		 * product.setQuantity(productDto.getQuantity());
		 */
		
		//add modelmapper maven dependency in pom.xml
		
		Product product = mapper.map(productDto, Product.class);
		
        Product save = productRepository.save(product);
        if(ObjectUtils.isEmpty(save)) //save =! null
        {
        	return false;
        }
		return true;
	}

	@Override
	public List<ProductDto> getAllProdct() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> productsDtoList = products.stream().map(Product->mapper.map(Product, ProductDto.class)).collect(Collectors.toList());
		return productsDtoList;
	}

	@Override
	public ProductDto getProductById(Integer id) {
		Optional<Product> findByProduct = productRepository.findById(id);
		if(findByProduct.isPresent()) {
			Product product = findByProduct.get();
			ProductDto productDto = mapper.map(product, ProductDto.class);
		return productDto;
		}
		return null;
	}

	@Override
	public Boolean deleteProduct(Integer id) {
		Optional<Product> findByProductId = productRepository.findById(id);
		if(findByProductId.isPresent()) {
			Product product = findByProductId.get();
			productRepository.delete(product);
			return true;
		}
		return false;
	}

}
