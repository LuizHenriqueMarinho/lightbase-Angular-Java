package com.produtos.apirest.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity; //ctrl + shift + O => importa tudo 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //envia os parametros para o banco de dados e gera a tabela
@Table(name = "tb_pessoa") //atualiza o nome da tabela 
public class Pessoa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@Column(nullable = false, unique = true)  //nullable define se vai ser obrigatorio, unique define que não pode se repitir no banco
	private String cpf;
	private int idade; //só pode remover pessoas maiores de 18
	private double peso;
	private String bairro;
	private String cidade;
	private String estado;
	private String dataNasc; 
	
	public Pessoa()
	{
		
	}
	
	public Pessoa(Long id, String nome, String cpf, int idade, double peso, String bairro, String cidade, String estado,
			String dataNasc) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.peso = peso;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.dataNasc = dataNasc;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cidade, cpf, dataNasc, estado, id, idade, nome, peso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(dataNasc, other.dataNasc)
				&& Objects.equals(estado, other.estado) && Objects.equals(id, other.id) && idade == other.idade
				&& Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso);
	}

}
