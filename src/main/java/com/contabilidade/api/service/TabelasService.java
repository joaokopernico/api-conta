package com.contabilidade.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contabilidade.api.domain.tabelas.fixas.DAS;
import com.contabilidade.api.domain.tabelas.fixas.IRRF;
import com.contabilidade.api.repository.dasRepository;
import com.contabilidade.api.repository.irrfRepository;

@Service

public class TabelasService {

	
	@Autowired
	dasRepository dasRepository;
	
	@Autowired
	irrfRepository irrfRepository;
	
	public List<DAS> saveDas(List<DAS> das) {
		
		
		List<DAS> dasList = dasRepository.saveAll(das);
		
		return dasList;
								
		
	}
	
	public List<IRRF> saveIrrf(List<IRRF> irrf) {
		
		
		List<IRRF> irrfList = irrfRepository.saveAll(irrf);
		
		return irrfList;
								
		
	}
	
	
	public List<DAS> findByAnexo(int anexo){
		
		return dasRepository.findByAnexo(anexo);		
		
	}
	
	public List<IRRF> findAllIrrf(){
		
		return irrfRepository.findAll();
	}
	
}
