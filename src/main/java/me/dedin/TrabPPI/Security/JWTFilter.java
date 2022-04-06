package me.dedin.TrabPPI.Security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import me.dedin.TrabPPI.Service.CookieService;

public class JWTFilter extends OncePerRequestFilter {

	private CookieService cookieService;

	public JWTFilter(CookieService cookieService) {
		this.cookieService = cookieService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = cookieService.recuperaToken(request);

		try {
			if (token != null && !token.isEmpty()) {
				JWTObject tokenObject = JWTCreator.create(token, SecurityConfig.KEY);

				List<SimpleGrantedAuthority> authorities = authorities(tokenObject.getRoles());

				UsernamePasswordAuthenticationToken userToken = 
						new UsernamePasswordAuthenticationToken(tokenObject.getId(), null, authorities);

				SecurityContextHolder.getContext().setAuthentication(userToken);

			} else {
				
				SecurityContextHolder.clearContext();
			}
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
			
//			response.sendRedirect("/login");
			
//			response.setStatus(HttpStatus.FORBIDDEN.value());
			return;
		}
	}

	private List<SimpleGrantedAuthority> authorities(List<String> roles) {
		return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
}
