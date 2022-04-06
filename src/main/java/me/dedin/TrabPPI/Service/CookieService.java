package me.dedin.TrabPPI.Service;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dedin.TrabPPI.Security.JWTCreator;
import me.dedin.TrabPPI.Security.JWTObject;
import me.dedin.TrabPPI.Security.SecurityConfig;
import me.dedin.TrabPPI.db.model.User;

@Service
public class CookieService {

	
	@Autowired
    private SecurityConfig securityConfig;
	
	public Cookie recuperaCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Cookie cook = null;
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					cook = cookie;
				}
			}
		}
		cook.setMaxAge(0);
		return cook;
	}
	
	public String recuperaToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String token = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					token = cookie.getValue();
				}
			}
		}
		return token;
	}
	
	public String criaToken(User user) {
        JWTObject jwtObject = new JWTObject();
        jwtObject.setId(user.getId().toString());
        jwtObject.setSubject(user.getNome().toString());
        jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
        jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
        jwtObject.setRoles(user.getRoles());
        String token = JWTCreator.create(SecurityConfig.KEY, jwtObject);
        return token;
	}
	
}
