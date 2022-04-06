package me.dedin.TrabPPI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import me.dedin.TrabPPI.db.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping("")
	public String paginaAnuncio(String nome){
//		Optional<Categoria> cat = categoriaRepository.findByName(nome);
//		if(cat.isPresent())
			return "categoria.html";
//		else
//			return "index.html";
	}
	
	
	
	
}
