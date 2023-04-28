package com.pessoalProjeto.clienteCrud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoalProjeto.clienteCrud.dto.ClienteDTO;
import com.pessoalProjeto.clienteCrud.entities.Cliente;
import com.pessoalProjeto.clienteCrud.repositories.ClienteRepository;
import com.pessoalProjeto.clienteCrud.services.exceptions.EntidadeNaoEncontrada;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Transactional(readOnly = true)
	public List<ClienteDTO> findAll(){
		
		List<Cliente> listaCliente = repository.findAll();
		
		List<ClienteDTO> listadto = new ArrayList<>();
		
		for (Cliente cat : listaCliente) {
			listadto.add(new ClienteDTO(cat));
		}
		
		return listadto;
		
		
	}

	@Transactional(readOnly = true)
	public ClienteDTO findById(Long id) {
		
		Optional<Cliente> obj = repository.findById(id);
		
		Cliente entidade = obj.orElseThrow(() -> new EntidadeNaoEncontrada("Entidade não encontrada"));
		
		return new ClienteDTO(entidade);
	}

	@Transactional
	public ClienteDTO insert(ClienteDTO dto) {
		
		Cliente entidade = new Cliente();
		
		entidade.setName(dto.getName());
		entidade.setBirthDate(dto.getBirthDate());
		entidade.setCpf(dto.getCpf());
		entidade.setChildren(dto.getChildren());
		entidade.setIncome(dto.getIncome());
		
		entidade = repository.save(entidade);
		
		
		return new ClienteDTO(entidade);
	}
	
	
}
