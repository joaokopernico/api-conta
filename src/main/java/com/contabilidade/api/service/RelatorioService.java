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
import com.contabilidade.api.domain.tabelas.SimplesNacional;
import com.contabilidade.api.domain.tabelas.fixas.DAS;
import com.contabilidade.api.domain.tabelas.fixas.IRRF;
import com.contabilidade.api.repository.SimplesNacionalRepository;

@Service
public class RelatorioService {

	
	@Autowired
	SimplesNacionalRepository simplesRepository;
	
	@Autowired
	FaturamentoService fatService;
	
	@Autowired
	ClienteService cliService;
	
	@Autowired
	TabelasService tabService;
	
	
	
	
	
	
	public List<SimplesNacional> findAll(){
		
		return simplesRepository.findAll();
	}
	
	
	public List<SimplesNacional> geraSimples(Cliente cliente, YearMonth date){
				
		// recebendo faturamentos
		List<Faturamento> faturamentos = fatService.findByCliente(cliente.getId());		
		SimplesNacional teste = new SimplesNacional();		
		List<Faturamento> fatAnoBase = new ArrayList<>();
		List<Faturamento> fatAnoAnterior = new ArrayList<>();
		
		
		// Separa ano atual do ano anterior
		
		for(Faturamento faturamento : faturamentos) {
			
			if(faturamento.getData().getYear() == date.getYear()) {
			
				
				fatAnoBase.add(faturamento);
			
			} else if(faturamento.getData().getYear() == date.minusYears(1).getYear()) {
				fatAnoAnterior.add(faturamento);
				
			}
						
		}
		
		SimplesNacional simples = new SimplesNacional();
						
		
		// completa meses com media
		
		if(date.getYear() == date.plusMonths(1).getYear()) {
			
			BigDecimal media = new BigDecimal(0);
			
			for (Faturamento fatAno : fatAnoBase) {
				
				media = media.add(fatAno.getValor());
				
			}

			// System.out.println(media);
			
			media = media.divide(new BigDecimal(fatAnoBase.size()), 2, RoundingMode.HALF_UP);
			
			// preenche o faturamento ano base
			
			
			List<Faturamento> tempList = new ArrayList<>();
			int control = fatAnoBase.size();
			
			for(int i = 1; control < 12;i++) {
				
			    Faturamento temp = new Faturamento();
			    
			    temp.setValor(media);
			    temp.setData(date.plusMonths(i));
			    temp.setFaturamento(false);			    
			    temp.setCliente(cliente);
			    temp.setFolha(media.multiply(new BigDecimal(0.28)).setScale(2, RoundingMode.HALF_UP));
			    tempList.add(temp);
			    
			    fatAnoBase.add(temp);
			    control++;
			  
			}
			
			fatService.savePrevisao(tempList);			
			
		}
		
		List<SimplesNacional> response = new ArrayList<>();
		
		for(Faturamento faturamento : fatAnoBase) {
			
			
			
	
		
		SimplesNacional tempSimples = new SimplesNacional();
		
		// calcula os ultimos 12 meses de faturamento
		
		BigDecimal soma12ultimosFat = new BigDecimal(0);
			
		for (var i = 1; i <= 12;i++) {

			for (Faturamento fat12 : faturamentos) {
				
				//System.out.println(faturamento.getData());
			//	System.out.println(date.minusMonths(i));
								
				if(fat12.getData().compareTo(faturamento.getData().minusMonths(i)) == 0) {
					
					//System.out.println(faturamento.getData() + "somado");
					soma12ultimosFat = soma12ultimosFat.add(fat12.getValor());
					
					
				} else {
					//System.out.println("Faturamento nao encontrado");
					
				}
			
			}
			
			tempSimples.setData(faturamento.getData());
			tempSimples.setRBT12(soma12ultimosFat);
			
			
			
				
		}
		
		
		// Calcula ultimos 12 meses da folha
		
		BigDecimal soma12ultimosTFS12 = new BigDecimal(0);
		
		//System.out.println(faturamento.getData().minusMonths(12));
		for (var i = 1; i <= 12;i++) {
			
			for (Faturamento fat12TFS : faturamentos) {
				
				//System.out.println(faturamento.getData());
				
				if(fat12TFS.getData().compareTo(faturamento.getData().minusMonths(i)) == 0) {
					
					//System.out.println(faturamento.getData() + "somado");
					
					//System.out.println(fat12TFS.getFolha());
					soma12ultimosTFS12 = soma12ultimosTFS12.add(fat12TFS.getFolha());
					
					
				} else {
					//System.out.println("Faturamento nao encontrado");
					
				}
				
			}
			
			tempSimples.setTFS12(soma12ultimosTFS12);
			
			
			
		}
		
		
		//Define fator r
		
		BigDecimal fatorR = soma12ultimosTFS12.divide(soma12ultimosFat, 4, RoundingMode.HALF_UP);
		tempSimples.setFatorR(fatorR);
		
		
		// define anexo
		//System.out.println(fatorR.compareTo(new BigDecimal("0.28")));
		
		BigDecimal calcDas = new BigDecimal("0");
		BigDecimal valorDas = new BigDecimal("0");
		
		if(fatorR.compareTo(new BigDecimal("0.28")) < 0) {
			
			System.out.println("Fator R menor");
			
			
			
		} else if(fatorR.compareTo(new BigDecimal("0.28")) >= 0) {
			
			
			
		
		
		
		// Checa Faixa do Anexo DAS e Calcula percentual
		
		List<DAS> dasList = tabService.findByAnexo(3);
		
		tempSimples.setAnexo(3);
		
		System.out.println(dasList);
		for(DAS das : dasList) {
			
			
			if(soma12ultimosFat.compareTo(das.getInicio()) > 0 && soma12ultimosFat.compareTo(das.getFim()) < 0) {
				
				
				
				
				calcDas = soma12ultimosFat.multiply(das.getAliquota()).subtract(das.getDeduzir()).divide(soma12ultimosFat, 6, RoundingMode.HALF_UP);
				valorDas = faturamento.getValor().multiply(calcDas);
				
				
				tempSimples.setPercentDAS(calcDas);
				tempSimples.setDAS(valorDas.setScale(2, RoundingMode.HALF_UP));
			
				
			}
			
		}
			
		
		}
		
		
		// calculo CPP & Prolabore
		
		BigDecimal cpp = new BigDecimal("0");
		BigDecimal dasXcpp = calcDas.multiply(new BigDecimal("0.434"));
		BigDecimal prolabore = new BigDecimal("0");
		BigDecimal inss = new BigDecimal("0");
		BigDecimal tetoInss = new BigDecimal("7507.49");
		
		cpp = faturamento.getValor().multiply(dasXcpp);
		//System.out.println(cpp);
		prolabore = faturamento.getFolha().subtract(cpp);
		//System.out.println(prolabore);
		
		
		if(prolabore.compareTo(tetoInss) < 0) {
			
			inss = prolabore.multiply(new BigDecimal("0.11").setScale(2, RoundingMode.HALF_UP));
					
			
		}else if(prolabore.compareTo(tetoInss) >= 0) {
			inss = tetoInss.multiply(new  BigDecimal("0.11"));
		}
		
		tempSimples.setInss(inss.setScale(2, RoundingMode.HALF_UP));
		
	
		// Calcula IRRF
		
		
		
		BigDecimal baseIRRF = prolabore.subtract(inss);
		List<IRRF> tabelasIrrf = tabService.findAllIrrf();
		BigDecimal valorIRRF = new BigDecimal("0");
		
		
		for(IRRF irrf : tabelasIrrf) {
			
			if(faturamento.getData().compareTo(irrf.getDataInicio()) >= 0 && faturamento.getData().compareTo(irrf.getDataFim()) <= 0) {
				
				if(baseIRRF.compareTo(irrf.getValorInicio()) >= 0 && baseIRRF.compareTo(irrf.getValorFim()) <= 0) {
					
					
					
					valorIRRF = baseIRRF.multiply(irrf.getAliquota()).subtract(irrf.getDeduzir());				
					tempSimples.setIr(valorIRRF.setScale(2, RoundingMode.HALF_UP));
					
					
//					System.out.println(faturamento.getData());
//					System.out.println(baseIRRF);
//					System.out.println(irrf.getValorInicio());
//					System.out.println(irrf.getFaixa());
//					System.out.println(irrf.getDeduzir());
				}
			}
			
		}
		
		
		// Calcula Total e % Total
		
		
		BigDecimal total = valorDas.add(inss).add(valorIRRF);
		BigDecimal percentTotal = total.divide(faturamento.getValor(), 6, RoundingMode.HALF_UP);
		System.out.println(percentTotal);
		
		tempSimples.setTotal(total.setScale(2, RoundingMode.HALF_UP));
		tempSimples.setPercentTotal(percentTotal);
			
		
		
		tempSimples.setFaturamento(faturamento);			
		response.add(tempSimples);
		
		
		
		}
		
		

		
		
		
		return response;
		

		
	
		
		
	}
	
	
}
