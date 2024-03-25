package com.hexaware.exception;


public class OrderNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OrderNotFoundException() {
		System.out.println("Order Not Found !!!");
	}
	public String toString() {
		return "OrderNotFoundException";
	}
}

