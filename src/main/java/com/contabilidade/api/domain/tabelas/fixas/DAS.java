package com.contabilidade.api.domain.tabelas.fixas;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table


public class DAS {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private int faixa;
	private BigDecimal inicio;
	private BigDecimal fim;
	@Column(precision=10, scale=4)
	private BigDecimal aliquota;
	private BigDecimal deduzir;
	private int anexo;
	
	
	public int getFaixa() {
		return faixa;
	}
	public void setFaixa(int faixa) {
		this.faixa = faixa;
	}
	public BigDecimal getInicio() {
		return inicio;
	}
	public void setInicio(BigDecimal inicio) {
		this.inicio = inicio;
	}
	public BigDecimal getFim() {
		return fim;
	}
	public void setFim(BigDecimal fim) {
		this.fim = fim;
	}
	public BigDecimal getAliquota() {
		return aliquota;
	}
	public void setAliquota(BigDecimal aliquota) {
		this.aliquota = aliquota;
	}
	public BigDecimal getDeduzir() {
		return deduzir;
	}
	public void setDeduzir(BigDecimal deduzir) {
		this.deduzir = deduzir;
	}
	public int getAnexo() {
		return anexo;
	}
	public void setAnexo(int anexo) {
		this.anexo = anexo;
	}
	
	

}
