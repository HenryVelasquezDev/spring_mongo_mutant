package com.example.mutant.exceptions;

public class EstructuraNoValidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstructuraNoValidaException(String mensaje) {
		super( mensaje );
	}
}
