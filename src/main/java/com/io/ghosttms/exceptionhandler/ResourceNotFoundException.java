package com.io.ghosttms.exceptionhandler;

import java.util.Arrays;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String object;
	private String resource;
	private String data;
	private String message;

	

	public ResourceNotFoundException(String object, String resource, String data, String message) {
		super();
		this.object = object;
		this.resource = resource;
		this.data = data;
		this.message = message;
	}


	public ResourceNotFoundException(String object, String resource, String data) {
		super();
		this.object = object;
		this.resource = resource;
		this.data = data;
	}



	@Override
	public String toString() {
		return "ResourceNotFoundException [getMessage()=" + getMessage() + ", getLocalizedMessage()="
				+ getLocalizedMessage() + ", getCause()=" + getCause() + ", toString()=" + super.toString()
				+ ", fillInStackTrace()=" + fillInStackTrace() + ", getStackTrace()=" + Arrays.toString(getStackTrace())
				+ ", getSuppressed()=" + Arrays.toString(getSuppressed()) + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	
	

}
