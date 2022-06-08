package com.example.mutant.exceptions;

public class ErrorDTO {
	
	private String mensaje;
	
	public ErrorDTO( String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
