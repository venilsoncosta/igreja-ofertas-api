package com.venilson.ofertas_igreja.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.venilson.ofertas_igreja.exceptions.dto.ErroPadraoDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(DoacaoOfertaException.class)
    public ResponseEntity<ErroPadraoDTO> handleDoacaoOfertaException(DoacaoOfertaException ex, 
        HttpServletRequest request) {
        ErroPadraoDTO erro = new ErroPadraoDTO(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Recurso não encontrado",
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadraoDTO> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {

        String mensagemDeErro = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        
        ErroPadraoDTO erro = new ErroPadraoDTO(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Erro de Validação",
            mensagemDeErro,
            request.getRequestURI()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
