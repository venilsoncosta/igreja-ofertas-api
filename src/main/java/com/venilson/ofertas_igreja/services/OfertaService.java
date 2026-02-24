package com.venilson.ofertas_igreja.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.venilson.ofertas_igreja.dto.OfertaRequestDTO;
import com.venilson.ofertas_igreja.dto.OfertaResponseDTO;
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
    public OfertaResponseDTO fazerOferta(OfertaRequestDTO dto){

        Igreja igreja = igrejaRepository.findById(dto.igrejaID())
                                    .orElseThrow(() -> new DoacaoOfertaException("Igreja não encontrada"));
        
        igreja.adicionarSaldo(dto.valorOfertado());

        Oferta oferta = new Oferta();
        oferta.setDoador(dto.doador());
        oferta.setTelefoneDoador(dto.telefoneDoador());
        oferta.setValorOfertado(dto.valorOfertado());
        oferta.setDataDoacao(LocalDateTime.now());
        oferta.setIgreja(igreja);

        Oferta ofertaSalva = ofertaRepository.save(oferta);

        return mapToResponse(ofertaSalva);
    }

    public List<OfertaResponseDTO> listarOfertas() {
        return ofertaRepository.findAll()
                                .stream()
                                .map(this::mapToResponse)
                                .toList();
    }

    private OfertaResponseDTO mapToResponse(Oferta oferta) {
        return new OfertaResponseDTO(
            oferta.getId(),
            oferta.getDoador(),
            oferta.getValorOfertado(),
            oferta.getDataDoacao(),
            oferta.getIgreja().getNome()
        );
    }
    
}
