package me.dedin.TrabPPI.db.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name= "tab_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
	private Long id;
	
	@NotNull @NotBlank(message = "Digite um nome valido")
	private String nome;
		
	@NotNull @NotBlank(message = "Digite um sobrenome valido")
	private String sobrenome;
	
	@NotBlank(message = "Digite uma senha valida")
	private String senha;
	
	@Column(unique = true)
	@NotBlank(message = "E-mail invalido") @Email(message = "E-mail invalido")
	private String email;
	
//	@Length(min = 5, message = "min 5")
	@Pattern(regexp = "[0-9]{10,11}", message = "Telefone invalido. ex: 34901234567")
	private String telefone;
	
	@Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "CEP invalido. ex: 00000-000")
	private String cep;
	
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();

	@OneToMany
	private List<Anuncio> anuncio;

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
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public List<Anuncio> getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(List<Anuncio> anuncio) {
		this.anuncio = anuncio;
	}
	
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
