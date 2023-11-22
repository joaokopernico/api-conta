package com.contabilidade.api.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record dasDto(@NotBlank Integer faixa, @NotBlank BigDecimal inicio, @NotBlank BigDecimal fim, 
		@NotBlank BigDecimal aliquota, @NotBlank BigDecimal deduzir, @NotBlank int anexo ) {

}
