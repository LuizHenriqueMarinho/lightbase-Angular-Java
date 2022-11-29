package com.devsuperior.myfirstproject.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category implements Serializable{ //serializable serve para habilitar que um objeto de uma determinada classe possa ter seu estado persistido pela api padrão do java
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // tem que clicar com o botao direito no nome da classe e selecionar add default serial version ID
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@JsonIgnore  //pre processamento para não serializar a lista de produtos de uma categoria (evita loop infinito)
	@OneToMany(mappedBy = "category") //o oposto de @ManyToOne
	private List<Product> products = new ArrayList<>();
	
	public Category()
	{
		
	}
	
	public Category(Long id, String name) {
		super(); //é o mesmo que this pata superclasses, está relacionado com herança
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
}
