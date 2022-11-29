package com.produtos.apirest.resourses;

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

import com.produtos.apirest.entities.Pessoa;
import com.produtos.apirest.repository.PessoaRepository;
import com.produtos.apirest.services.PessoaService;

@RestController  //faz o mesmo que o @Controller e o @RestponseBody juntos. @Controller: é o responsável por controlar as requisições indicando quem deve receber as requisições para quem deve responde-las. @RestponseBody é usada para transformar um objeto Java retornado do controller em uma representação de recurso solicitada por um cliente REST
@RequestMapping(value="/pessoas") //localhost:8080
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll() //@PathVariable serve para reconhecer o numero que está no fim subtituindo o id no postman
	{
		return ResponseEntity.ok().body(pessoaService.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id)
	{
		return ResponseEntity.ok().body(pessoaService.findById(id)); 
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa)
	{
		return new ResponseEntity<>(pessoaService.save(pessoa), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa, @PathVariable Long id)
	{
		return new ResponseEntity<>(pessoaService.update(pessoa, id), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id)
	{
		pessoaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
