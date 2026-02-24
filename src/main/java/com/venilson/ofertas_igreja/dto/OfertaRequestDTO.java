package com.venilson.ofertas_igreja.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OfertaRequestDTO(
    @NotBlank(message = "O nome não pode estar em branco")
    String doador,
    @NotNull @Positive(message = "O valor deve ser positivo")
    BigDecimal valorOfertado,
    @NotBlank(message = "Telefone obrigatório para registro")
    String telefoneDoador,
    @NotNull
    Long igrejaID
) {
    
}
