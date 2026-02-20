package com.venilson.ofertas_igreja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venilson.ofertas_igreja.model.Igreja;

public interface IgrejaRepository extends JpaRepository<Igreja, Long>{
    
}
