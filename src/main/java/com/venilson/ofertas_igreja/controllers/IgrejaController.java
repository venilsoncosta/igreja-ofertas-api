package com.venilson.ofertas_igreja.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venilson.ofertas_igreja.model.Igreja;
import com.venilson.ofertas_igreja.services.IgrejaService;

@RestController
@RequestMapping("/igrejas")
public class IgrejaController {
    
    private final IgrejaService igrejaService;

    public IgrejaController(IgrejaService igrejaService) {
        this.igrejaService = igrejaService;
    }

    @PostMapping
    public ResponseEntity<Igreja> criarIgreja(@RequestBody Igreja igreja) {
        Igreja novaIgreja = igrejaService.criarIgreja(igreja);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaIgreja);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Igreja> buscarIgreja(@PathVariable Long id){
        
        return igrejaService.buscarIgrejaPorId(id)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Igreja>> listarIgrejas(){
        return ResponseEntity.ok(igrejaService.buscarIgreja());
    }


}
