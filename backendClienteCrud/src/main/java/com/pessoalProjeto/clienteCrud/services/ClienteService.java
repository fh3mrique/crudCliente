package com.pessoalProjeto.clienteCrud.services;



import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoalProjeto.clienteCrud.dto.ClienteDTO;
import com.pessoalProjeto.clienteCrud.entities.Cliente;
import com.pessoalProjeto.clienteCrud.repositories.ClienteRepository;
import com.pessoalProjeto.clienteCrud.services.exceptions.BancoDeDadoException;
import com.pessoalProjeto.clienteCrud.services.exceptions.EntidadeNaoEncontrada;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAllPaged(PageRequest pageRequest) {

		Page <Cliente> listaCliente = repository.findAll(pageRequest);
		
		
		return listaCliente.map(x -> new ClienteDTO(x));

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

		CopiarDtoParaEntidade(entidade, dto);

		entidade = repository.save(entidade);

		return new ClienteDTO(entidade);
	}

	@Transactional
	public ClienteDTO update(Long id, ClienteDTO dto) {
		try {

			Cliente entidade = repository.getOne(id);

			CopiarDtoParaEntidade(entidade, dto);

			entidade = repository.save(entidade);

			return new ClienteDTO(entidade);
		}

		catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontrada("id não encontrado");
		}
	}

	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrada("id não encontrado");
		}
		catch(DataIntegrityViolationException e) {
			throw new BancoDeDadoException("violação na integridade do banco");
		}
	}

	private void CopiarDtoParaEntidade(Cliente entidade, ClienteDTO dto) {
		entidade.setName(dto.getName());
		entidade.setCpf(dto.getCpf());
		entidade.setIncome(dto.getIncome());
		entidade.setBirthDate(dto.getBirthDate());
		entidade.setChildren(dto.getChildren());

	}

}
