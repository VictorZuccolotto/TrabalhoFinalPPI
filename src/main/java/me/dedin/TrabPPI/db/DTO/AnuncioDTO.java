package me.dedin.TrabPPI.db.DTO;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.websocket.OnMessage;

import org.hibernate.validator.constraints.Range;

import me.dedin.TrabPPI.db.model.Anuncio;

public class AnuncioDTO {

	public AnuncioDTO() {
		
	}
	
	
	public AnuncioDTO(Anuncio anuncio) {
		this.id = anuncio.getId();
		this.nome = anuncio.getNome();
		this.preco = anuncio.getPreco();
		this.descricao = anuncio.getDescricao();
		this.nomeUser = anuncio.getUser().getNome();
		this.telefone = anuncio.getUser().getTelefone();
		this.userID = anuncio.getUser().getId();
	}

	private Long id;
	
	@NotBlank(message = "Digite um nome valido")
	private String nome;
	
	@NotNull(message = "Digite um preço valido")
	private Double preco;
	
	private String descricao;
	
	@NotNull(message = "Selecione uma categoria") @Range(max = 7, min = 1, message = "Selecione uma categoria")
	private Long categoria;
	
	private String nomeUser;
	
	private String telefone;
	
	private Long userID;
	
	@AssertTrue(message = "Adicione uma imagem")
	private boolean imagem;

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Double getPreco() {
		return preco;
	}


	public void setPreco(Double preco) {
		this.preco = preco;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getNomeUser() {
		return nomeUser;
	}


	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCategoria() {
		return categoria;
	}


	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}


	public boolean isImagem() {
		return imagem;
	}


	public void setImagem(boolean imagem) {
		this.imagem = imagem;
	}


	public Long getUserID() {
		return userID;
	}


	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
	
}
