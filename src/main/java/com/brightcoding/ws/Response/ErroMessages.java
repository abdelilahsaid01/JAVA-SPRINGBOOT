package com.brightcoding.ws.Response;

public enum ErroMessages {
	
	MISSING_REQUIERED_FIELD("Missing required field"),
	RECORD_ALREADY_EXISTS("Record already exists"),
	INTERNAL_SERVER_ERROR("Internal Bright coding error"),
	NO_RECORD_FOUND("Record with id is not found");

	private String errorMessage;

	private ErroMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	
}
