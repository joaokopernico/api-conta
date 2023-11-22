package com.contabilidade.api.service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contabilidade.api.domain.Cliente;
import com.contabilidade.api.domain.Faturamento;
import com.contabilidade.api.repository.FaturamentoRepository;
import com.contabilidade.api.service.exception.DataException;
import com.contabilidade.api.service.exception.NotFoundException;

@Service

public class FaturamentoService {

	@Autowired
	FaturamentoRepository faturamentoRepository;
	
	
	public Optional<Faturamento> findById(UUID id) {
		
		Optional<Faturamento> response = faturamentoRepository.findById(id);
		if(response.isEmpty()) {
			throw new NotFoundException("Faturamento não encontrado!");
		}
		
		return response;
	}
	
	public List<Faturamento> findAll(){
		
		return faturamentoRepository.findAll();
	}
	
	public Optional<Faturamento> findByDataCliente(YearMonth data, UUID id){
		
		Optional<Faturamento> resultado = faturamentoRepository.findByDataCliente( data, id);
		
		return resultado; 
	}
	
	public List<Faturamento> findByCliente(UUID id){
		
		List<Faturamento> resultado = faturamentoRepository.findByCliente(id);
		
		return resultado; 
	}
	
	public Iterable<Faturamento> saveNew(Iterable<Faturamento> faturamentos) {
		
		Iterable<Faturamento> savedFaturamentos = new ArrayList<>();
		List<String> exception = new ArrayList<>();
		Class<?> add = null;

		for(Faturamento faturamento : faturamentos) {
			
			Optional<Faturamento> fatCliente = findByDataCliente(faturamento.getData(), faturamento.getCliente().getId());
			
			UUID fatClienteUUID = fatCliente.map(Faturamento :: getId).orElse(UUID.randomUUID());
			Boolean isTrue = findByDataCliente(faturamento.getData(), faturamento.getCliente().getId()).map(Faturamento :: getFaturamento).orElse(true);
									
			faturamento.setFaturamento(true);
			
			faturamento.setFolha(faturamento.getValor().multiply(new BigDecimal(0.28)).setScale(2, RoundingMode.HALF_UP));
			
			if(!fatCliente.isEmpty() && isTrue == true){
			
				if(isTrue == false) {
					
					System.out.println(fatClienteUUID);
					faturamentoRepository.deleteById(fatClienteUUID);
					
					continue;

				} else {
					
					exception.add("Faturamento não inserido " + faturamento.getData() + " - Duplicado! " );
					
					continue;
				}

			}
					
			
			((List<Faturamento>) savedFaturamentos).add(faturamentoRepository.save(faturamento));
			
		}
		
		if(!exception.isEmpty()) {
			
			String exceptionString = String.join(" - ", exception);
	
			throw new DataException(exceptionString);
			
		}
		
		return faturamentoRepository.saveAll(savedFaturamentos);
	}
	
	
public Faturamento saveEdit(Faturamento faturamento) {
		
		// Verifica se o faturamento existe
			
			Optional<Faturamento> faturamento0 = findById(faturamento.getId());
			if(faturamento0.isEmpty()) { 
					
				throw new DataException("FATURAMENTO NÃO ENCONTRADO");
				
			}
			
			faturamento0.get().setValor(faturamento.getValor());
			
		
		return faturamentoRepository.save(faturamento0.get());
	}
	
public Iterable<Faturamento> savePrevisao(Iterable<Faturamento> faturamentos) {
		
		Iterable<Faturamento> savedFaturamentos = new ArrayList<>();
		List<String> exception = new ArrayList<>();

		for(Faturamento faturamento : faturamentos) {
			
			Optional<Faturamento> fatCliente = findByDataCliente(faturamento.getData(), faturamento.getCliente().getId());
			
			UUID fatClienteUUID = fatCliente.map(Faturamento :: getId).orElse(UUID.randomUUID());
			Boolean isTrue = findByDataCliente(faturamento.getData(), faturamento.getCliente().getId()).map(Faturamento :: getFaturamento).orElse(true);
									
			faturamento.setFaturamento(false);
			
			if(!fatCliente.isEmpty() && isTrue == true){
			
				if(isTrue == false) {
					
					System.out.println(fatClienteUUID);
					faturamentoRepository.deleteById(fatClienteUUID);
					
					continue;

				} else {
					
					exception.add("Faturamento não inserido " + faturamento.getData() + " - Duplicado! " );
					
					continue;
				}

			}
					
			
			((List<Faturamento>) savedFaturamentos).add(faturamentoRepository.save(faturamento));
			
		}
		
		if(!exception.isEmpty()) {
			
			String exceptionString = String.join(" - ", exception);
	
			throw new DataException(exceptionString);
			
		}
		
		return faturamentoRepository.saveAll(savedFaturamentos);
	}

	public String delete(UUID id) {
	
		Optional<Faturamento> faturamento = findById(id);

		if(faturamento.isEmpty()) {
			throw new DataException("CLIENTE NÃO ENCONTRADO!");
		}
	
		faturamentoRepository.delete(faturamento.get());
	
		return "Faturamento deletado com sucesso!";
	
	
		}
	
	
}
