package com.contabilidade.api.dtos;



import jakarta.validation.constraints.NotBlank;

public record NewClienteDto(@NotBlank String nome,@NotBlank String cnpj,@NotBlank String estado) {

}
