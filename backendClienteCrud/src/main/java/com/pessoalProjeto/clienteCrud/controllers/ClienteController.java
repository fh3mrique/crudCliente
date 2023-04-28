package com.pessoalProjeto.clienteCrud.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoalProjeto.clienteCrud.dto.ClienteDTO;
import com.pessoalProjeto.clienteCrud.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findCliente(){
		
		//List<Cliente> listaCliente = new ArrayList<>();
		
//		listaCliente.add(new Cliente(1L, "Filipe", "71538709465", 100.00, Instant.parse("1999-01-01T00:00:00.00Z"), 2));
		
		List<ClienteDTO> listaCliente = service.findAll();
		
		return ResponseEntity.ok().body(listaCliente);
	}
}
