package me.dedin.TrabPPI.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ValidacaoHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationException(MethodArgumentNotValidException ex){
		List<String> erros = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((erro) -> {
			String errorMessage = erro.getDefaultMessage();
			erros.add(errorMessage);
		});
		return erros;
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SQLException.class)
	public List<String> handleSQLException(SQLException ex){
		 List<String> errors = new ArrayList<String>();
		 errors.add("E-mail ja registrado");
		return errors;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ ConstraintViolationException.class })
	public List<String> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        errors.add(violation.getMessage());
	    }
	    System.out.println(errors);
		return errors;
	}

	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(ConstraintViolationException.class)
//	public List<String> handleValidationException2(ConstraintViolationException ex){
//		List<String> erros = new ArrayList<>();
//		ex.getBindingResult().getAllErrors().forEach((erro) -> {
//			String errorMessage = erro.getDefaultMessage();
//			erros.add(errorMessage);
//		});
//		return erros;
//	}
//	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
//		Map<String, String> erros = new HashMap<String, String>();
//		
//		ex.getBindingResult().getAllErrors().forEach((erro) -> {
//			String fieldName = ((FieldError) erro).getField();
//			String errorMessage = erro.getDefaultMessage();
//			erros.put(fieldName, errorMessage);
//		});
//		return erros;
//	}
//	
}
