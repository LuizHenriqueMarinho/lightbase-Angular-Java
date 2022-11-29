package com.produtos.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos.apirest.entities.Pessoa;
import com.produtos.apirest.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	public List<Pessoa> findAll(){
		return repository.findAll();
	}
	
	public Pessoa findById(Long id)
	{
		Pessoa obj = repository.findById(id).get();
		return obj;
	}
	
	public Pessoa save(Pessoa pessoa)
	{
		return repository.save(pessoa);
	}
	
	public Pessoa update(Pessoa pessoa, Long id)
	{
		Pessoa pessoaAtualizada = findById(id);
		pessoaAtualizada.setNome(pessoa.getNome());
		pessoaAtualizada.setCpf(pessoa.getCpf());
		pessoaAtualizada.setIdade(pessoa.getIdade());
		pessoaAtualizada.setPeso(pessoa.getPeso());
		pessoaAtualizada.setNome(pessoa.getNome());
		pessoaAtualizada.setBairro(pessoa.getBairro());
		pessoaAtualizada.setCidade(pessoa.getCidade());
		pessoaAtualizada.setEstado(pessoa.getEstado());
		
		return repository.save(pessoaAtualizada);

	}
	
	public void delete(Long id)
	{
		Pessoa verificaIdade = findById(id);
		if(verificaIdade.getIdade() >= 18)
		{
			repository.deleteById(id);
		}
		else
		{
			System.out.println("usuario precisa ser maior de idade");
		}
	}
	
	
}
