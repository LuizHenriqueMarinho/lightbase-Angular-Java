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

import com.devsuperior.myfirstproject.entities.Category;
import com.devsuperior.myfirstproject.repositories.CategoryRepository;
import com.devsuperior.myfirstproject.services.CategoryService;

	@RestController //o spring boot passa a reconhecer como recurso rest
	@RequestMapping(value = "/categories") //caminho
	public class CategoryResource {
		
		//injeção de dependencia
		@Autowired //faz um pre processamento por baixo dos panos e resolve a instancia, depende do @component no CategoryRepository
		private CategoryRepository categoryRepository;
		
		@Autowired
		private CategoryService categoryService;
		
		@GetMapping //requisição get
		public ResponseEntity<List<Category>> findAll() { //é o tipo do retorno
			//está sendo executado no service// List<Category> list = categoryRepository.findAll(); //vai acessar os dados na memoria ou banco de dados, buscar e retornar
			//return ResponseEntity.ok().body(list); //o ok() tem a ver com o 200 do http na requisição do front	e o body recebe o conteudo	
			return ResponseEntity.ok().body(categoryService.findAll()); //
		}
		

		@GetMapping(value = "/{id}")
		public ResponseEntity<Category> findById(@PathVariable Long id) { //@PathVariable serve para reconhecer o numero que está no fim subtituindo o id no postman
			//Category cat = categoryRepository.findById(id).get();
			return ResponseEntity.ok().body(categoryService.findById(id)); 
		}
		
		@PostMapping(value = "/save")
		public ResponseEntity<Category> save(@RequestBody Category categoria) // receber um objeto do tipo cateogira criado pelo usuario
		{
			return new ResponseEntity<>(categoryService.save(categoria), HttpStatus.CREATED); //new ResponseEntity = ResponseEntity.ok(.body() e o HttpStatus.CREATED => ok 201
		}
		
		@PutMapping(value = "/update/{id}")  //precisa receber o id do item que será editado
		public ResponseEntity<Category> update(@RequestBody Category categoria, @PathVariable Long id)
		{
			return new ResponseEntity<>(categoryService.update(categoria, id), HttpStatus.ACCEPTED);
		}
		
		@DeleteMapping(value = "/delete/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id)
		{
			categoryService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
}

