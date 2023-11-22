package com.contabilidade.api.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDto(@NotNull UUID id, @NotBlank String nome,@NotBlank String cnpj,@NotBlank String estado) {

}
