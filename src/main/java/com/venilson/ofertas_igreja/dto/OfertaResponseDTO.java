package com.venilson.ofertas_igreja.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OfertaResponseDTO(
    Long id,
    String doador,
    BigDecimal valorOfertado,
    LocalDateTime dataDoacao,
    String nomeIgreja
) {}
