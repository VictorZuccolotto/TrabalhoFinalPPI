package me.dedin.TrabPPI.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.dedin.TrabPPI.Service.UserService;
import me.dedin.TrabPPI.db.model.User;
import me.dedin.TrabPPI.db.repository.UserRepository;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService service;
	
	
	@PostMapping("")
	@ResponseBody
//	@Transactional
	public ResponseEntity<String> registrarUsuario(@RequestBody @Valid User user){
		service.createUser(user);
		//APOS REGISTRO LOGAR USUARIO
		return ResponseEntity.ok("");
	}
	
	
	
	

	
	
}
