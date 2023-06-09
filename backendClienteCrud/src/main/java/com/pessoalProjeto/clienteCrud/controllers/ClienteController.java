package com.pessoalProjeto.clienteCrud.controllers;


import java.net.URI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pessoalProjeto.clienteCrud.dto.ClienteDTO;
import com.pessoalProjeto.clienteCrud.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> findCliente(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy
			){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy  );
		
		Page<ClienteDTO> listaCliente = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(listaCliente);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById( @PathVariable Long id){
		
		ClienteDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto);
		
	}
	
	
	@PostMapping
	public ResponseEntity<ClienteDTO> insert (@RequestBody ClienteDTO dto){
		
		 dto = service.insert(dto);
		 
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(dto.getId()).toUri();
		
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update (@PathVariable Long id, @RequestBody ClienteDTO dto ){
		
		dto = service.update(id, dto);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	
	}

}
