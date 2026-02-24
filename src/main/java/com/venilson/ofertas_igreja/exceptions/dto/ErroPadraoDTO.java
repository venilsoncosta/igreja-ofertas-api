package com.venilson.ofertas_igreja.exceptions.dto;

import java.time.LocalDateTime;

public record ErroPadraoDTO(
    LocalDateTime timestamp,
    Integer status,
    String erro,
    String mensagem,
    String path
) {
}
