package me.dedin.TrabPPI.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import me.dedin.TrabPPI.db.DTO.Sessao;
import me.dedin.TrabPPI.db.model.Anuncio;
import me.dedin.TrabPPI.db.repository.AnuncioRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	AnuncioRepository anuncioRepository;

	@GetMapping("")
	public String home() {
		return "index.html";
	}
	
	@GetMapping("/criar-anuncio")
	public String paginaCriarAnuncio(){
		return "criar-anuncio.html";
	}
	
	@GetMapping("/minha-conta")
	public String paginaMinhaConta(){
		return "minha-conta.html";
	}
	
	@GetMapping("/cadastro")
	public String paginaRegistro() {
		return "cadastro.html";
	}
	
	@GetMapping("/anuncio")
	public String paginaAnuncio(Long id) {
		return "anuncio.html";
	}
	
	@GetMapping("/usuario")
	public String paginaUsuario() {
		return "usuario.html";
	}
	
	@GetMapping("/meus-anuncios")
	public String paginaMeusAnuncios() {
		return "meus-anuncios.html";
	}
	
	@GetMapping("/imagem/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Anuncio anuncio)
			throws ServletException, IOException {
		try {
			anuncio = anuncioRepository.findById(id).get();
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(anuncio.getImagem());
			response.getOutputStream().close();
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
}
