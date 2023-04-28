package com.pessoalProjeto.clienteCrud.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		List<ClienteDTO> listaCliente = service.findAll();
		
		return ResponseEntity.ok().body(listaCliente);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById( @PathVariable Long id){
		
		ClienteDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto);
		
	}

}
