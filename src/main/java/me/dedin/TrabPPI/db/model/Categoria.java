package me.dedin.TrabPPI.db.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne
	private Categoria superCategoria;
	
	@OneToMany
	private List<Categoria> subCategoria;
	
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

	public Categoria getSuperCategoria() {
		return superCategoria;
	}

	public void setSuperCategoria(Categoria superCategoria) {
		this.superCategoria = superCategoria;
	}

	public List<Categoria> getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(List<Categoria> subCategoria) {
		this.subCategoria = subCategoria;
	}

	
	
}
