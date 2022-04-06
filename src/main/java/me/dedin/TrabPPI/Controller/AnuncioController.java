package me.dedin.TrabPPI.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import me.dedin.TrabPPI.Security.SecurityConfig;
import me.dedin.TrabPPI.Service.CookieService;
import me.dedin.TrabPPI.db.DTO.AnuncioDTO;
import me.dedin.TrabPPI.db.model.Anuncio;
import me.dedin.TrabPPI.db.model.Categoria;
import me.dedin.TrabPPI.db.repository.AnuncioRepository;
import me.dedin.TrabPPI.db.repository.CategoriaRepository;
import me.dedin.TrabPPI.db.repository.UserRepository;

@RestController
@Validated
@RequestMapping("/anuncio")
public class AnuncioController {

	@Autowired
	CookieService cookieService;
	
	@Autowired
	AnuncioRepository anuncioRepository;

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	UserRepository userRepository;
    
		
	@PostMapping("/criarAnuncioDTO")
	public ResponseEntity<?> criaAnuncioDTO(@RequestBody @Valid AnuncioDTO anuncio){
		return ResponseEntity.ok(null);
	}
	
	@Transactional
	@PostMapping("/criarAnuncio")
	public ResponseEntity<?> criarAnuncio(HttpServletRequest request,
			@RequestParam("nome") String nome,
			@RequestParam("categoria") Long categoria,
			@RequestParam("preco") Double preco,
			@RequestParam("descricao") String descricao,
			final @RequestParam("file") MultipartFile imagem) {
			
		String token = cookieService.recuperaToken(request);

		if (token == null || token.isEmpty()) {
			return null;
		}
		Claims claims = Jwts.parser().setSigningKey(SecurityConfig.KEY).parseClaimsJws(token).getBody();
		
		
		try {
			String fileName = imagem.getOriginalFilename();
			if (fileName == null || fileName.contains("..") || fileName.isBlank()) {
				List<String> lista = new ArrayList<String>();
				lista.add("Selecione alguma foto");
				return new ResponseEntity<>(lista,HttpStatus.BAD_REQUEST);
			}
			byte[] imageData = imagem.getBytes();
			Anuncio anuncio = new Anuncio();
			anuncio.setNome(nome);
			anuncio.setImagem(imageData);
			anuncio.setPreco(preco);
			anuncio.setUser(userRepository.findById(Long.parseLong(claims.getId())).get());
			anuncio.setDescricao(descricao);
			anuncio.setCategoria(categoriaRepository.getById(categoria));
			anuncio.setData(new Date());
			anuncioRepository.save(anuncio);
			return new ResponseEntity<>("Anuncio salvo", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/anunciosRecentes")
	@ResponseBody
	public List<Anuncio> anunciosRecentes() {
		List<Anuncio> lista = anuncioRepository.findFirst9ByOrderByDataDesc();
//		for (Anuncio anuncio : lista) {
//			System.out.println(anuncio.g);
//		}
		return lista;
	}

	@GetMapping("/info/{id}")
	@ResponseBody
	public AnuncioDTO info(@PathVariable("id") Long id) {
		return new AnuncioDTO(anuncioRepository.findById(id).get());
	}

//	@GetMapping("/cat/{nome}")
//	@ResponseBody
//	public List<Anuncio> anunciosByName(@PathVariable("nome") String name) {
//		Optional<Categoria> optional = categoriaRepository.findByName(name);
//		if(!optional.isPresent())
//			return null;
//		Sort sort = Sort.by("data").descending();
//		PageRequest paginacao = PageRequest.of(0, 3, sort);
//		
//		return anuncioRepository.findByCategoriaName(name, paginacao);
//		
//	}
	@GetMapping("/nome/{nome}")
	@ResponseBody
	public ResponseEntity<List<Anuncio>> anunciosByName(@PathVariable("nome") String name) {
		Sort sort = Sort.by("data").descending();
		PageRequest paginacao = PageRequest.of(0, 3, sort);
		Optional<List<Anuncio>> optional = anuncioRepository.findByNomeContaining(name, paginacao);
		try {
			if (optional.get().isEmpty()) {
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(optional.get());

	}

	@GetMapping("/cat/{nome}")
	@ResponseBody
	public ResponseEntity<List<Anuncio>> anunciosByCatName(@PathVariable("nome") String name) {
		Optional<Categoria> optional = categoriaRepository.findByName(name);
		if (!optional.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		Sort sort = Sort.by("data").descending();
		PageRequest paginacao = PageRequest.of(0, 3, sort);

		List<Anuncio> lista = anuncioRepository.findByCategoriaName(name, paginacao);
		if(lista.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(lista);

	}

//	@GetMapping("")
//	public String paginaAnuncio(Long id) {
//		return "anuncio.html";
//	}

	@GetMapping("/{nome}")
	public String paginaAnuncio(@PathVariable("nome") String nome) {
		Optional<Categoria> cat = categoriaRepository.findByName(nome);
		if (cat.isPresent())
			return "categorias.html";
		else
			System.out.println("aqui");
		return "index.html";
	}

}
