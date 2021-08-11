package com.sayvaz.productservice.repository;

import java.util.List;
import java.util.Optional;

import com.sayvaz.productservice.entity.Product;

public interface ProductRepository {
	
	public List<Product> findAll();
	
	public Optional<Product> findByProductId(Integer productId);
	
	public Product save(Product product);
	
	public void delete(Integer productId);
	
	public void update(Product product);

}
