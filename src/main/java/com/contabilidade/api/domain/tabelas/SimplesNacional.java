package com.contabilidade.api.domain.tabelas;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

import org.hibernate.annotations.GeneratorType;

import com.contabilidade.api.domain.Cliente;
import com.contabilidade.api.domain.Faturamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table


public class SimplesNacional {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private YearMonth data;
	@OneToOne
	private Faturamento faturamento;
	private BigDecimal RBT12;
	private BigDecimal TFS12;
	private BigDecimal FatorR;
	private Integer anexo;
	private BigDecimal PercentDAS;
	private BigDecimal DAS;
	private BigDecimal inss;
	private BigDecimal ir;
	private BigDecimal total;
	private BigDecimal percentTotal; // total / faturamento
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public YearMonth getData() {
		return data;
	}
	public void setData(YearMonth data) {
		this.data = data;
	}
	public Faturamento getFaturamento() {
		return faturamento;
	}
	public void setFaturamento(Faturamento faturamento) {
		this.faturamento = faturamento;
	}
	public BigDecimal getRBT12() {
		return RBT12;
	}
	public void setRBT12(BigDecimal rBT12) {
		RBT12 = rBT12;
	}
	public BigDecimal getTFS12() {
		return TFS12;
	}
	public void setTFS12(BigDecimal tFS12) {
		TFS12 = tFS12;
	}
	public BigDecimal getFatorR() {
		return FatorR;
	}
	public void setFatorR(BigDecimal fatorR) {
		FatorR = fatorR;
	}
	public Integer getAnexo() {
		return anexo;
	}
	public void setAnexo(Integer anexo) {
		this.anexo = anexo;
	}
	public BigDecimal getPercentDAS() {
		return PercentDAS;
	}
	public void setPercentDAS(BigDecimal percentDAS) {
		PercentDAS = percentDAS;
	}
	public BigDecimal getDAS() {
		return DAS;
	}
	public void setDAS(BigDecimal dAS) {
		DAS = dAS;
	}
	public BigDecimal getInss() {
		return inss;
	}
	public void setInss(BigDecimal inss) {
		this.inss = inss;
	}
	public BigDecimal getIr() {
		return ir;
	}
	public void setIr(BigDecimal ir) {
		this.ir = ir;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getPercentTotal() {
		return percentTotal;
	}
	public void setPercentTotal(BigDecimal percentTotal) {
		this.percentTotal = percentTotal;
	}

	
	
	
}
