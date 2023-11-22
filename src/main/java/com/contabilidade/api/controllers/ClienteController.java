package com.contabilidade.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.contabilidade.api.domain.Cliente;
import com.contabilidade.api.dtos.NewClienteDto;
import com.contabilidade.api.service.ClienteService;

import jakarta.validation.Valid;

@RestController
public class ClienteController {

    @Autowired
	ClienteService clienteService;

    @PostMapping("/cliente")
	public ResponseEntity<Cliente> saveCliente(@RequestBody @Valid NewClienteDto clienteDto) {
		
		var clienteModel = new Cliente();
		BeanUtils.copyProperties(clienteDto, clienteModel);
			

			
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteModel));
				
	}

    
	@GetMapping("/cliente")
	public ResponseEntity<List<Cliente>> getClientes() {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
	}
	
	
	@GetMapping("/cliente/{idcliente}")
	public ResponseEntity<Optional<Cliente>> getById(@PathVariable String idcliente)  {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(UUID.fromString(idcliente)));
	}
	
	@PutMapping("/cliente/{id}")
		public ResponseEntity<Cliente> updateCliente(@PathVariable String id, @RequestBody @Valid NewClienteDto clienteDto) {
			
		 	
			var clienteModel = new Cliente();
			BeanUtils.copyProperties(clienteDto, clienteModel);		
			clienteModel.setId(UUID.fromString(id));
						
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.update(clienteModel));
					
		}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable String id) {
		
	 	
				
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.delete(UUID.fromString(id)));
				
	}
	

    
}
