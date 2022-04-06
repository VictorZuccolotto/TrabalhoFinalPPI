package me.dedin.TrabPPI.Controller;


import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import me.dedin.TrabPPI.Security.SecurityConfig;
import me.dedin.TrabPPI.Service.CookieService;
import me.dedin.TrabPPI.db.DTO.Login;
import me.dedin.TrabPPI.db.model.User;
import me.dedin.TrabPPI.db.repository.UserRepository;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private CookieService cookieService;
	
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private SecurityConfig securityConfig;
    
    @Autowired
    private UserRepository repository;

    @PostMapping("")
    public ResponseEntity<?> logar(@RequestBody Login login, HttpServletResponse response){
//    	System.out.println(login.getEmail() + " e " + login.getSenha());
    	try {
    	
	        Optional<User> user = repository.findByEmail(login.getEmail());
	        if(user.isPresent()) {
	            boolean passwordOk =  encoder.matches(login.getSenha(), user.get().getSenha());
	        	if(passwordOk) {
	        		Cookie cookie = new Cookie("token", cookieService.criaToken(user.get()));
	        		cookie.setMaxAge(SecurityConfig.EXPIRATION/1000);
	        		response.addCookie(cookie);
	        		return ResponseEntity.ok("");
	        	}
	        }
    	}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
    	return ResponseEntity.badRequest().build();
    }
    
    @GetMapping("")
    public String paginaLogin() {
    	return "login.html";
    }
}
