package com.devsuperior.myfirstproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.myfirstproject.entities.Category;
import com.devsuperior.myfirstproject.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id)
	{
		Category obj = repository.findById(id).get();
		return obj;
	}
	
	public Category save(Category category)
	{
		return repository.save(category);
	}
	
	public Category update(Category category, Long id)
	{
		Category categoryUptaded = findById(id);
		categoryUptaded.setName(category.getName()); 
		return repository.save(categoryUptaded);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
