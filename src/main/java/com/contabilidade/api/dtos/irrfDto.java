package com.contabilidade.api.dtos;

import java.math.BigDecimal;
import java.time.YearMonth;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public record irrfDto(@NotBlank Integer faixa, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy") @DateTimeFormat(pattern = "MM-yyyy") YearMonth dataInicio,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy") @DateTimeFormat(pattern = "MM-yyyy") YearMonth dataFim, @NotBlank BigDecimal valorInicio, 
		@NotBlank BigDecimal valorFim, @NotBlank BigDecimal aliquota, @NotBlank BigDecimal deduzir) {

}
