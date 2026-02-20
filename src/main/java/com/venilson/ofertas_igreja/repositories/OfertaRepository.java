package com.venilson.ofertas_igreja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venilson.ofertas_igreja.model.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long>{
    
}
