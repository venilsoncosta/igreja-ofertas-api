package com.venilson.ofertas_igreja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.venilson.ofertas_igreja.model.Igreja;
import com.venilson.ofertas_igreja.repositories.IgrejaRepository;

@Service
public class IgrejaService {
    
    private final IgrejaRepository igrejaRepository;

    public IgrejaService(IgrejaRepository igrejaRepository) {
        this.igrejaRepository = igrejaRepository;
    }

    public Igreja criarIgreja(Igreja igreja) {
        return igrejaRepository.save(igreja);
    }

    public List<Igreja> buscarIgreja(){
        return igrejaRepository.findAll();
    }

    public Optional<Igreja> buscarIgrejaPorId(Long id){
        return igrejaRepository.findById(id);
    }
}
