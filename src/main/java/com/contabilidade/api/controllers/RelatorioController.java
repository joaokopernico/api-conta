package com.contabilidade.api.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.contabilidade.api.domain.Faturamento;
import com.contabilidade.api.domain.tabelas.SimplesNacional;
import com.contabilidade.api.domain.tabelas.fixas.DAS;
import com.contabilidade.api.dtos.NewFaturamentoDto;
import com.contabilidade.api.dtos.RelatorioDto;
import com.contabilidade.api.service.RelatorioService;

import jakarta.validation.Valid;

@RestController
public class RelatorioController {
	
	@Autowired
	RelatorioService relService;
	
	@PostMapping("/relatorio")
	public List<SimplesNacional> saveFaturamento(@RequestBody @Valid RelatorioDto relatorio) {
		

		
		List<SimplesNacional> resultado = relService.geraSimples(relatorio.cliente(), relatorio.data());
		
		return resultado;
		
	
		
		
	}

}
