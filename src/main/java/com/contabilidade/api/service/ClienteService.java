package com.contabilidade.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contabilidade.api.domain.Cliente;
import com.contabilidade.api.repository.ClienteRepository;
import com.contabilidade.api.service.exception.DataException;

@Service

public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	private Cliente map;
	
	
	public Optional<Cliente> findByCnpj(String cnpj){
		return clienteRepository.findByCnpj(cnpj);
	}
	
	public Optional<Cliente> findById(UUID id) {
		
		return clienteRepository.findById(id);
		
	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente save(Cliente cliente) {
		
		
		Optional<Cliente> teste = findByCnpj(cliente.getCnpj());
		UUID uidTeste = teste.map(Cliente :: getId).orElse(UUID.randomUUID());
		Boolean booTeste = cliente.getId() == uidTeste;
		
		if(!findByCnpj(cliente.getCnpj()).isEmpty()) {
			System.out.println(cliente.getId());
			System.out.println(uidTeste);
			System.out.println(booTeste);
			throw new DataException("CNPJ JA EXISTE");
				}
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		
		
		// Verifica se o cliente mencionado existe
		if(findById(cliente.getId()).isEmpty()) { 
				
			throw new DataException("CLIENTE NÃO ENCONTRADO");
			
		}
		
		// verifica o CNPJ
		Optional<Cliente> getClientes = findByCnpj(cliente.getCnpj());
		UUID checaDuplicidade = getClientes.map(Cliente :: getId).orElse(UUID.randomUUID());
		int validaDup = cliente.getId().compareTo(checaDuplicidade);
		
		
		
		// Checa se o cnpj inserido ja existe
		if(!findByCnpj(cliente.getCnpj()).isEmpty() && validaDup != 0) {
			
			System.out.println(cliente.getId());
			System.out.println(checaDuplicidade);
			System.out.println(validaDup);
			throw new DataException("CNPJ JA EXISTE");
		}
		
		return clienteRepository.save(cliente);
	}
	
	public String delete(UUID id) {
		
		Optional<Cliente> cliente = findById(id);
	
		if(cliente.isEmpty()) {
			throw new DataException("CLIENTE NÃO ENCONTRADO!");
		}
		
		clienteRepository.delete(cliente.get());
		
		return "Cliente deletado com sucesso!";
		
		
	}
	
}
