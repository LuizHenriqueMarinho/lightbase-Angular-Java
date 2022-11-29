package com.devsuperior.myfirstproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.myfirstproject.entities.Product;
import com.devsuperior.myfirstproject.repositories.ProductRepository;
import com.devsuperior.myfirstproject.services.ProductService;

	@RestController //o spring boot passa a reconhecer como recurso rest
	@RequestMapping(value = "/products") //caminho
	public class ProductResource {
		
		//injeção de dependencia
		@Autowired //faz um pre processamento por baixo dos panos e resolve a instancia, depende do @component no ProductRepository
		private ProductRepository productRepository;
		
		@Autowired
		private ProductService productService;
		
		@GetMapping //requisição get
		public ResponseEntity<List<Product>> findAll() {
			List<Product> list = productRepository.findAll(); //vai acessar os dados na memoria ou banco de dados, buscar e retornar
			return ResponseEntity.ok().body(list); //o ok() tem a ver com o 200 do http na requisição do front	e o body recebe o conteudo	
			}

		@GetMapping(value = "/{id}")
		public ResponseEntity<Product> findById(@PathVariable Long id) { //@PathVariable serve para reconhecer o numero que está no finsubtituindo o id no postman
			Product prod = productRepository.findById(id).get();
			return ResponseEntity.ok().body(prod); 
		}
		
		@PostMapping(value = "/save")
		public ResponseEntity<Product> save(@RequestBody Product prod)
		{
			return new ResponseEntity<>(productService.save(prod), HttpStatus.CREATED);
		}
		
		@PutMapping(value = "/{id}")
		public ResponseEntity<Product> update(@RequestBody Product prod, @PathVariable Long id)
		{
			return new ResponseEntity<>(productService.update(prod, id), HttpStatus.ACCEPTED);
		}
		
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id)
		{
			productService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
}

