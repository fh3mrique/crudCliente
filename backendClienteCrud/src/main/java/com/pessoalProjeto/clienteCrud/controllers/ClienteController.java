package com.pessoalProjeto.clienteCrud.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoalProjeto.clienteCrud.entities.Cliente;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@GetMapping
	public ResponseEntity<List<Cliente>> findCliente(){
		
		List<Cliente> listaCliente = new ArrayList<>();
		
		listaCliente.add(new Cliente(1L, "Filipe", "71538709465", 100.00, Instant.parse("1999-01-01T00:00:00.00Z"), 2));
		listaCliente.add(new Cliente(2L, "Pedro", "71538709466", 100.00, Instant.parse("1999-01-01T00:00:00.00Z"), 1));
		
		return ResponseEntity.ok().body(listaCliente);
	}
}
