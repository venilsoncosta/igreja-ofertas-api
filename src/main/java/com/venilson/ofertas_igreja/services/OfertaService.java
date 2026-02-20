package com.venilson.ofertas_igreja.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.venilson.ofertas_igreja.exceptions.DoacaoOfertaException;
import com.venilson.ofertas_igreja.model.Igreja;
import com.venilson.ofertas_igreja.model.Oferta;
import com.venilson.ofertas_igreja.repositories.IgrejaRepository;
import com.venilson.ofertas_igreja.repositories.OfertaRepository;

@Service
public class OfertaService {
    
    private final OfertaRepository ofertaRepository;
    private final IgrejaRepository igrejaRepository;

    public OfertaService(OfertaRepository ofertaRepository, IgrejaRepository igrejaRepository) {
        this.ofertaRepository = ofertaRepository;
        this.igrejaRepository = igrejaRepository;
    }

    @Transactional
    public Oferta fazerOferta(Oferta oferta){
        validarOferta(oferta);

        Igreja igreja = igrejaRepository.findById(oferta.getIgreja().getId())
                                    .orElseThrow(() -> new DoacaoOfertaException("Igreja não encontrada"));
        
        igreja.setSaldo(igreja.getSaldo().add(oferta.getValorOfertado()));
        
        igrejaRepository.save(igreja);

        oferta.setDataDoacao(LocalDateTime.now());

        return ofertaRepository.save(oferta);
    }

    public List<Oferta> listarOfertas() {
        return ofertaRepository.findAll();
    }


    public void validarOferta(Oferta oferta){
        if (oferta.getDoador() == null 
            || oferta.getValorOfertado() == null
            || oferta.getValorOfertado().compareTo(BigDecimal.ZERO) <= 0
            || oferta.getTelefoneDoador() == null
            || oferta.getTelefoneDoador().isBlank()) {

                throw new DoacaoOfertaException("Dados da oferta inválidos");
            }

    }
    
}
