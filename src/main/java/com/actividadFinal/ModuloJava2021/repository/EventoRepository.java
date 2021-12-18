package com.actividadFinal.ModuloJava2021.repository;

import com.actividadFinal.ModuloJava2021.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository <Evento, Long> {

}
