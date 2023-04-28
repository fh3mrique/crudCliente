package com.pessoalProjeto.clienteCrud.services.exceptions;

public class EntidadeNaoEncontrada extends RuntimeException{

	private static final long serialVersionUID = 1L;
 
	
	public EntidadeNaoEncontrada(String msg) {
		super (msg);
	}
	
}
