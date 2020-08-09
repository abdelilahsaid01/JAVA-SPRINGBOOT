package com.brightcoding.ws.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	
	@NotNull(message="Ce champ ne doit pas etre null")	//Le champs qui doit existe peut etre vide mais note null
	@Size(min=3,message="Ce champ doit avoir au moins 3 caractéres")
	private String firstName;
	@NotEmpty(message="Ce champ ne doit pas etre vide")	//Le champs peut etre non vide par les espaces
	@Size(min=3,message="Ce champ doit avoir au moins 3 caractéres")
	private String lastName;
	@NotNull(message="Ce champ ne doit pas etre null")
	@Email(message="Ce champ doit respecter la forme email")
	private String email;
	@NotBlank(message="Ce champ ne doit pas etre avec des caractéres vides")	//Les vides ne sont pas prise en  compte, le champs doit contenir des caractéres visibles
	@Size(min=8,message="Ce champ doit avoir au moins 8 caractéres")
	@Size(max=12,message="Ce champ doit avoir au max 12 caractéres")
	@Pattern(regexp ="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message="Ce champ doit avoir des Maj et Mins et Number et 8 Carc au min" )	//Permet d'appliquer des expréssions régulières
	private String password;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
