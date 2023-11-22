package com.contabilidade.api.domain.tabelas.fixas;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table

public class IRRF {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public UUID id;
	private int faixa;
	@Column
	public YearMonth dataInicio;
	@Column
	public YearMonth dataFim;
	public BigDecimal valorInicio;
	public BigDecimal valorFim;
	@Column(precision=10, scale=4)
	public BigDecimal aliquota;
	public BigDecimal deduzir;
	public int getFaixa() {
		return faixa;
	}
	public void setFaixa(int faixa) {
		this.faixa = faixa;
	}
	public YearMonth getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(YearMonth dataInicio) {
		this.dataInicio = dataInicio;
	}
	public YearMonth getDataFim() {
		return dataFim;
	}
	public void setDataFim(YearMonth dataFim) {
		this.dataFim = dataFim;
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
	public BigDecimal getValorInicio() {
		return valorInicio;
	}
	public void setValorInicio(BigDecimal valorInicio) {
		this.valorInicio = valorInicio;
	}
	public BigDecimal getValorFim() {
		return valorFim;
	}
	public void setValorFim(BigDecimal valorFim) {
		this.valorFim = valorFim;
	}
	
	

}
