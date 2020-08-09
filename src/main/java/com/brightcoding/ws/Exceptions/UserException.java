package com.brightcoding.ws.Exceptions;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = -4935451876628678737L;

	public UserException(String message) {	//Sper pour Initialiser le constructeur de la classe hérité
		super(message);
	}
	
}
