package com.contabilidade.api.controllers;

import java.util.ArrayList;
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

import com.contabilidade.api.domain.Faturamento;
import com.contabilidade.api.dtos.FaturamentoDto;
import com.contabilidade.api.dtos.NewFaturamentoDto;
import com.contabilidade.api.service.FaturamentoService;

import jakarta.validation.Valid;



@RestController
public class FaturamentoController {
	
	@Autowired
	FaturamentoService faturamentoService;
	
	
	
	
	@PostMapping("/faturamento")
	public ResponseEntity<Iterable<Faturamento>> saveFaturamento(@RequestBody @Valid List<NewFaturamentoDto> faturamentosDto) {
		
		
		
		List<Faturamento> faturamentosModel = new ArrayList<>();
		
		for (NewFaturamentoDto faturamentoDto : faturamentosDto) {
			
			System.out.println(faturamentoDto);
			var faturamentoModel = new Faturamento();			
			BeanUtils.copyProperties(faturamentoDto, faturamentoModel);		
			faturamentosModel.add(faturamentoModel);
			
		}
		
		Iterable<Faturamento> savedFaturamentos = faturamentoService.saveNew(faturamentosModel);
		
			
		
		// TODO enviar array de faturamento ao invez de um faturamento
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFaturamentos);
				
	}
	
	
	
	@GetMapping("/faturamento")
	public ResponseEntity<List<Faturamento>> getFaturamentos() {

	 	List<Faturamento> teste = faturamentoService.findAll();
		//System.out.println(teste.get(8).getCliente().getCnpj());

		//return ResponseEntity.status(HttpStatus.OK).body(null);
		return ResponseEntity.status(HttpStatus.OK).body(faturamentoService.findAll());
	}
	
	
	@GetMapping("/faturamento/{id}")
	public ResponseEntity<Faturamento> getFatById(@PathVariable String id) {

	 	Optional<Faturamento> response = faturamentoService.findById(UUID.fromString(id));
		//System.out.println(teste.get(8).getCliente().getCnpj());

		//return ResponseEntity.status(HttpStatus.OK).body(null);
		return ResponseEntity.status(HttpStatus.OK).body(response.get());
	}
	
	@PutMapping("/faturamento/{id}")
	public ResponseEntity<Faturamento> editFaturamento(@PathVariable String id, @RequestBody @Valid FaturamentoDto faturamentoDto) {
		
		
				
		System.out.println(faturamentoDto);
		Faturamento faturamentoModel = new Faturamento();		
		BeanUtils.copyProperties(faturamentoDto, faturamentoModel);			
		faturamentoModel.setId(UUID.fromString(id));
		
		System.out.println(faturamentoModel.getValor());
	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(faturamentoService.saveEdit(faturamentoModel));
				
	}
	
	@DeleteMapping("/faturamento/{id}")
	public ResponseEntity<String> deleteFaturamento(@PathVariable String id) {
		
	 				
		return ResponseEntity.status(HttpStatus.CREATED).body(faturamentoService.delete(UUID.fromString(id)));
				
	}
	



}
