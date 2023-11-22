package com.contabilidade.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.contabilidade.api.domain.Faturamento;
import com.contabilidade.api.domain.tabelas.fixas.DAS;
import com.contabilidade.api.domain.tabelas.fixas.IRRF;
import com.contabilidade.api.dtos.NewFaturamentoDto;
import com.contabilidade.api.dtos.dasDto;
import com.contabilidade.api.dtos.irrfDto;
import com.contabilidade.api.service.TabelasService;

import jakarta.validation.Valid;

@RestController

public class TabelasController {
	
	@Autowired
	TabelasService tabelaService;

	
	@PostMapping("/tabelas/das")
	public ResponseEntity<List<DAS>> saveDas(@RequestBody @Valid List<dasDto> dasList) {
		
		
		List<DAS> dasModels = new ArrayList<>();
		
		for (dasDto dasDto : dasList) {
			
			//System.out.println(dasDto);
			DAS dasModel = new DAS();			
			BeanUtils.copyProperties(dasDto, dasModel);		
			dasModels.add(dasModel);
			
		}
		
		List<DAS> savedDas = tabelaService.saveDas(dasModels);

		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedDas);
		
		
	}
	
	@PostMapping("/tabelas/irrf")
	public ResponseEntity<List<IRRF>> saveIrrf(@RequestBody @Valid List<irrfDto> irrfList) {
		
		
		List<IRRF> irrfModels = new ArrayList<>();
		
		for (irrfDto irrfDto : irrfList) {
			
			//System.out.println(dasDto);
			IRRF irrfModel = new IRRF();			
			BeanUtils.copyProperties(irrfDto, irrfModel);		
			irrfModels.add(irrfModel);
			
		}
		
		List<IRRF> savedIrrf = tabelaService.saveIrrf(irrfModels);

		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedIrrf);
		
		
	}
	
	
}
