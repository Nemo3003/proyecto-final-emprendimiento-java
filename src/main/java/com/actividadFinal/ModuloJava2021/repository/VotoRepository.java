package com.actividadFinal.ModuloJava2021.repository;

import com.actividadFinal.ModuloJava2021.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{
}
