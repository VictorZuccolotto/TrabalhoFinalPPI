package me.dedin.TrabPPI.db.DTO;

import me.dedin.TrabPPI.db.model.User;

public class UserDTO {

	
	public UserDTO() {}
	
	public UserDTO(User user) {
		this.nome = user.getNome();
		this.sobrenome = user.getSobrenome();
		this.telefone = user.getTelefone();
		this.cep = user.getCep();
	}

	private String nome;
	
	private String sobrenome;
	
	private String telefone;
	
	private String cep;
	
	private String senhaNew;
	
	private String senhaOld;

	
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

	public String getSenhaNew() {
		return senhaNew;
	}

	public void setSenhaNew(String senhaNew) {
		this.senhaNew = senhaNew;
	}

	public String getSenhaOld() {
		return senhaOld;
	}

	public void setSenhaOld(String senhaOld) {
		this.senhaOld = senhaOld;
	}
	
	
}
