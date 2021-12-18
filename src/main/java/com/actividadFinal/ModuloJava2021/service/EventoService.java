package com.actividadFinal.ModuloJava2021.service;

import com.actividadFinal.ModuloJava2021.entity.Evento;
import com.actividadFinal.ModuloJava2021.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Transactional
    public Evento crearEvento(Evento evento){return eventoRepository.save(evento);}
    @Transactional
    public void eliminarEvento(Long id){eventoRepository.deleteById(id);}
    @Transactional
    public Optional<Evento> buscarEventoId(Long id){return eventoRepository.findById(id);}
    @Transactional
    public List<Evento> allEventos(){return eventoRepository.findAll();}
}
