package com.venilson.ofertas_igreja.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venilson.ofertas_igreja.model.Oferta;
import com.venilson.ofertas_igreja.services.OfertaService;

@RestController
@RequestMapping("/ofertas")
public class OfertaController {
    
    private final OfertaService ofertaService;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @PostMapping
    public ResponseEntity<Oferta> criaOferta(@RequestBody Oferta oferta) {
        Oferta novaOferta = ofertaService.fazerOferta(oferta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOferta);
    }

    @GetMapping
    public ResponseEntity<List<Oferta>> listarOfertas() {
        return ResponseEntity.ok(ofertaService.listarOfertas());
    }

    
}
