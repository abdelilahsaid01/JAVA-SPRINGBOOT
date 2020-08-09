package com.brightcoding.ws.Exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.brightcoding.ws.Response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value= {UserException.class})	//La classe d'exception à executer. Vous pouvez montionné plusier classe pour déclancher cette méthode
	public ResponseEntity<Object> HandlerUserException(UserException ex, WebRequest request)
	{
		ErrorMessage erroMessage = new ErrorMessage(new Date(), ex.getMessage()); 	//pour afficher le message et sa date
		return new ResponseEntity<>(erroMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);	//Retourner le message d'erreur
	}
	
	@ExceptionHandler(value= Exception.class)	//La classe d'exception générale (Pas spécifique comme ci-dessus !!)
	public ResponseEntity<Object> HandlerOtherException(Exception ex, WebRequest request)
	{
		ErrorMessage erroMessage = new ErrorMessage(new Date(), ex.getMessage()); 	//pour afficher le message et sa date
		return new ResponseEntity<>(erroMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);	//Retourner le message d'erreur
	}
	
	@ExceptionHandler(value= MethodArgumentNotValidException.class)	//Gérer les exceptions de validation
	public ResponseEntity<Object> HandlerMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request)
	{
		
		Map<String,String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> 
		errors.put(error.getField(), error.getDefaultMessage()));
		
		return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);	//Error 400 cad les champs envoyées ne correspont pas aux régles
	}

}

