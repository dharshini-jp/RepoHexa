package com.hexaware.exception;

public class AdminNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AdminNotFoundException() {
		System.out.println("Admin Not Found !!!");
	}
	public String toString() {
		return "AdminNotFoundException";
	}
}


