package com.contabilidade.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="tb_faturamento")

public class Faturamento implements Serializable {
	
	private static final long serialversionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column
	private YearMonth data;
	private BigDecimal valor;
	private Boolean faturamento;
	@ManyToOne()
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	private BigDecimal folha;
	
	
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
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Boolean getFaturamento() {
		return faturamento;
	}
	public void setFaturamento(Boolean faturamento) {
		this.faturamento = faturamento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Faturamento() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	public BigDecimal getFolha() {
		return folha;
	}
	public void setFolha(BigDecimal folha) {
		this.folha = folha;
	}

	
}
