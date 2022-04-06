package me.dedin.TrabPPI.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import me.dedin.TrabPPI.Service.CookieService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CookieService cookieService;
	
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    private static final String[] SWAGGER_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable()
                .addFilterAfter(new JWTFilter(cookieService), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(SWAGGER_WHITELIST).permitAll()
                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.GET,"/css/**").permitAll()
                .antMatchers(HttpMethod.GET,"/js/**").permitAll()
                .antMatchers(HttpMethod.GET,"/img/**").permitAll()
                .antMatchers(HttpMethod.GET,"/anuncio/**").permitAll()
                .antMatchers(HttpMethod.GET,"/imagem/**").permitAll()
                .antMatchers(HttpMethod.GET,"/categoria").permitAll()
                .antMatchers("/cadastro").permitAll()
//                .antMatchers("/teste.html").permitAll()
                .antMatchers("/login").permitAll()
//                .antMatchers("/anuncio/criaAnuncioDTO").permitAll()
                .antMatchers("/user/**").hasAnyRole("USERS","MANAGERS")
                .antMatchers(HttpMethod.POST,"/anuncio/criarAnuncio").hasAnyRole("USERS","MANAGERS")
                .antMatchers(HttpMethod.GET,"/criar-anuncio").hasAnyRole("USERS")
                .antMatchers(HttpMethod.GET,"/meus-anuncios").hasAnyRole("USERS","MANAGERS")
//                .antMatchers("/managers").hasAnyRole("MANAGERS")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}