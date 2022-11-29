package com.devsuperior.myfirstproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.myfirstproject.entities.Category;
import com.devsuperior.myfirstproject.entities.Product;
import com.devsuperior.myfirstproject.repositories.ProductRepository;

@Service
public class ProductService {
	
	//@Autowired
	private ProductRepository repository;
	
	public ProductService(ProductRepository repository)
	{
		this.repository = repository;
	}
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id)
	{
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	
	public Product save(Product product)
	{
		return repository.save(product);
	}
	
	public Product update(Product product, Long id)
	{
		Product productUpdated = findById(id);
		productUpdated.setName(product.getName());
		productUpdated.setPrice(product.getPrice());
		productUpdated.setCategory(product.getCategory());
		return repository.save(productUpdated);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	
}
