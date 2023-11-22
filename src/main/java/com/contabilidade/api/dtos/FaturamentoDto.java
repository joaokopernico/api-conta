package com.contabilidade.api.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record FaturamentoDto(@NotNull BigDecimal valor) {

}
