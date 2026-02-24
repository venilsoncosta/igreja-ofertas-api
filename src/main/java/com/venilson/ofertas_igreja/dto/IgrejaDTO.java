package com.venilson.ofertas_igreja.dto;

import jakarta.validation.constraints.NotBlank;

public record IgrejaDTO(
    @NotBlank
    String nome
) {
    
}
