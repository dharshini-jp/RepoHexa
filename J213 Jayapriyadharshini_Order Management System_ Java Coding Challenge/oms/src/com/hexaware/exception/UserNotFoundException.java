package com.hexaware.exception;


public class UserNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserNotFoundException() {
		System.out.println("User Not Found !!!");
	}
	public String toString() {
		return "UserNotFoundException";
	}
}

