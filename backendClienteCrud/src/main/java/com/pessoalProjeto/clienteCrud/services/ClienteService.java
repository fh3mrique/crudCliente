package com.pessoalProjeto.clienteCrud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoalProjeto.clienteCrud.dto.ClienteDTO;
import com.pessoalProjeto.clienteCrud.entities.Cliente;
import com.pessoalProjeto.clienteCrud.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Transactional
	public List<ClienteDTO> findAll(){
		
		List<Cliente> listaCliente = repository.findAll();
		
		List<ClienteDTO> listadto = new ArrayList<>();
		
		for (Cliente cat : listaCliente) {
			listadto.add(new ClienteDTO(cat));
		}
		
		return listadto;
		
		
	}
	
	
}
