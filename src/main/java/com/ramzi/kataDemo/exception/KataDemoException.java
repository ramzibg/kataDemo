package com.ramzi.kataDemo.exception;

/**
 * @author ramzi
 * class exception for kata
 *
 */
public class KataDemoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7256412131673162675L;
	/**
	 * 
	 */
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public KataDemoException(String message) {
		this.message = message;
		System.err.println("########"+message );
	}

	public KataDemoException() {
			System.err.println("Operation Exception");		
	}


	
	
}
