package com.pessoalProjeto.clienteCrud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoalProjeto.clienteCrud.entities.Cliente;
import com.pessoalProjeto.clienteCrud.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public List<Cliente> findAll(){
		
		return repository.findAll();
	}
	
	
}
