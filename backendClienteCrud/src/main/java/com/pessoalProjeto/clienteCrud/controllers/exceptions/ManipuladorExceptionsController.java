package com.pessoalProjeto.clienteCrud.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pessoalProjeto.clienteCrud.services.exceptions.EntidadeNaoEncontrada;

@ControllerAdvice
public class ManipuladorExceptionsController {

	@ExceptionHandler(EntidadeNaoEncontrada.class)
	public ResponseEntity<MessagemErroPadrao> mensagemRequesicao(EntidadeNaoEncontrada e, HttpServletRequest request){
		
		MessagemErroPadrao err = new MessagemErroPadrao();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Entidade não encontrada");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
}
