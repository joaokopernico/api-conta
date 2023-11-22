package com.contabilidade.api.dtos;

import java.time.YearMonth;

import org.springframework.format.annotation.DateTimeFormat;

import com.contabilidade.api.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record RelatorioDto(@NotNull Cliente cliente, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy") @DateTimeFormat(pattern = "MM-yyyy") @NotNull YearMonth data) {

}
