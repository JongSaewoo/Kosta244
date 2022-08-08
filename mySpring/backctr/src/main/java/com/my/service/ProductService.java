package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductRepository;

@Service(value="productService")
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
//	public ProductService() {
//		
//	}
//	
//	public ProductService(ProductRepository repository) {
//		this.repository = repository;
//	}
	
	public List<Product> list() throws FindException {
		return repository.selectAll();
	}
	
	public Product view(String prodNo) throws FindException {
		return repository.selectByProdNo(prodNo);
	}
	
	public List<Product> search(String word) throws FindException{
		return repository.selectByProdNoOrProdName(word);
	}
	
	
	public ProductRepository getRepository() {
		return repository;
	}
	public void setRepository(ProductRepository repository) {
		this.repository = repository;
	}
}
