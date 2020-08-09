package com.brightcoding.ws.Response;

import java.util.Date;

public class ErrorMessage {
	private Date timestamp;
	private String message;
	
	

//	public ErrorMessage() {	C'est pas la peine d'utiliser le constructeur de la classe
//		super();
//		// TODO Auto-generated constructor stub
//	}

	public ErrorMessage(Date timestamp, String message) {	//Ce consctructeur permet d'initialiser les attributs et les utilisées
		super();
		this.timestamp = timestamp;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
