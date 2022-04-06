package me.dedin.TrabPPI.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import me.dedin.TrabPPI.Security.SecurityConfig;
import me.dedin.TrabPPI.Service.CookieService;
import me.dedin.TrabPPI.db.DTO.AnuncioDTO;
import me.dedin.TrabPPI.db.DTO.UserDTO;
import me.dedin.TrabPPI.db.model.Anuncio;
import me.dedin.TrabPPI.db.model.User;
import me.dedin.TrabPPI.db.repository.AnuncioRepository;
import me.dedin.TrabPPI.db.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AnuncioRepository anuncioRepository;

	@Autowired
	CookieService cookieService;

	@GetMapping("/getId")
	public String getId(HttpServletRequest request) {

		String token = cookieService.recuperaToken(request);

		if (token == null || token.isEmpty()) {
			return null;
		}
		Claims claims = Jwts.parser().setSigningKey(SecurityConfig.KEY).parseClaimsJws(token).getBody();
		return claims.getId();
	}

	@GetMapping("/getNome")
	public String getNome(HttpServletRequest request) {

		String token = cookieService.recuperaToken(request);

		if (token == null || token.isEmpty()) {
			return null;
		}
		Claims claims = Jwts.parser().setSigningKey(SecurityConfig.KEY).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	@GetMapping("/meusAnuncios")
	public ResponseEntity<List<AnuncioDTO>> meusAnuncios(HttpServletRequest request) {
		try {
			String token = cookieService.recuperaToken(request);
			Claims claims = Jwts.parser().setSigningKey(SecurityConfig.KEY).parseClaimsJws(token).getBody();
			Long userID = Long.parseLong(claims.getId());
			Sort sort = Sort.by("data").descending();
			PageRequest paginacao = PageRequest.of(0, 100, sort);
			List<Anuncio> lista = anuncioRepository.findByUserId(userID, paginacao);
			if (!lista.isEmpty() && lista != null) {
				List<AnuncioDTO> listaDTO = new ArrayList<AnuncioDTO>();
				for (Anuncio anuncio : lista) {
					AnuncioDTO a = new AnuncioDTO(anuncio);
					listaDTO.add(a);
				}
				return ResponseEntity.ok(listaDTO);
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@DeleteMapping("excluir/{id}")
	public ResponseEntity<?> excluirAnuncio(@PathVariable("id") String id, HttpServletRequest request) {

		try {
			String token = cookieService.recuperaToken(request);
			Claims claims = Jwts.parser().setSigningKey(SecurityConfig.KEY).parseClaimsJws(token).getBody();
			Long userID = Long.parseLong(claims.getId());
			Long anuncioID = Long.parseLong(id);
			Optional<Anuncio> anuncio = anuncioRepository.findById(anuncioID);
			if (anuncio.isPresent()) {
				if (userID == anuncio.get().getUser().getId()) {
					anuncioRepository.deleteById(anuncioID);
					return ResponseEntity.ok().build();
				}

			}

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/getUserDTO")
	public ResponseEntity<UserDTO> getUserDTO(HttpServletRequest request) {
		try {
			String token = cookieService.recuperaToken(request);
			Claims claims = Jwts.parser().setSigningKey(SecurityConfig.KEY).parseClaimsJws(token).getBody();
			Long userID = Long.parseLong(claims.getId());
			User user = userRepository.getById(userID);
			UserDTO userDTO = new UserDTO(user);
			return ResponseEntity.ok(userDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/atualizaUser")
	public ResponseEntity<?> atualizaUser(HttpServletRequest request, @RequestBody @Valid User user){
		
		System.out.println(user.getSenha());
//		try {
//			String token = cookieService.recuperaToken(request);
//			Claims claims = Jwts.parser().setSigningKey(SecurityConfig.KEY).parseClaimsJws(token).getBody();
//			Optional<User> user = userRepository.findById(Long.parseLong(claims.getId()));
//			if(!user.isPresent()) {
//				throw new Exception();
//			}
//		}catch (Exception e) {
//			return ResponseEntity.badRequest().build();
//		}
		return ResponseEntity.badRequest().build();	
	}
	

}
