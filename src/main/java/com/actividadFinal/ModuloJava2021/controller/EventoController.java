package com.actividadFinal.ModuloJava2021.controller;

import com.actividadFinal.ModuloJava2021.entity.Evento;
import com.actividadFinal.ModuloJava2021.entity.Voto;
import com.actividadFinal.ModuloJava2021.exception.ErrorInfo;
import com.actividadFinal.ModuloJava2021.service.EventoService;
import com.actividadFinal.ModuloJava2021.service.VotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;
    @Autowired
    private VotoService votoService;
    Logger logger = LoggerFactory.getLogger(ErrorInfo.class);
    @GetMapping(value = "/")
    public ResponseEntity<?> todosLosEventos() {
        List<Evento> eventos = eventoService.allEventos();
        if (!eventos.isEmpty()) {
            return ResponseEntity.ok(eventos);
        } else return new ResponseEntity<>("no se encuentra agregado ningún evento", HttpStatus.NOT_FOUND);
    }
    @PostMapping(value = "/")
    public ResponseEntity<?> crearEvento(@Valid @RequestBody Evento evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.crearEvento(evento));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> borrarEvento(@PathVariable(value = "id") int idEvento) {
        if (eventoService.buscarEventoId((long) idEvento).isPresent()) {
            eventoService.eliminarEvento((long) idEvento);
            return ResponseEntity.ok("El evento fue eliminado exitosamente");
        }
        return new ResponseEntity<>("No hay eventos identificados con esa id o ingresó un dato invalido", HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/{id}")
    ResponseEntity<?> modifiarEvento(@RequestBody @Valid Evento eventoModif, @PathVariable(value = "id") @Valid int idEvento) {
        Optional<Evento> evento = eventoService.buscarEventoId((long) idEvento);
        if (!evento.isPresent()) {
            return new ResponseEntity<>("No se encuentra ningún evento con esa id", HttpStatus.NOT_FOUND);
        }
        evento.get().setEstadoEvento(eventoModif.getEstadoEvento());
        evento.get().setDetallesEvento(eventoModif.getDetallesEvento());
        evento.get().setFechaCierre(eventoModif.getFechaCierre());
        evento.get().setPremio(eventoModif.getPremio());
        return ResponseEntity.ok(eventoService.crearEvento(evento.get()));
    }
    @GetMapping(value = "rankingPorEventoId/{id}")
    ResponseEntity<?> rankingEvento(@PathVariable(value = "id") int idEvento) {
        List<Voto> voto = votoService.buscarVotoPorEvento((long) idEvento);
        Map<String, Long> mapRanking = new HashMap<>();
        List<String> emprendimientos = new ArrayList<>();
        for (Voto x : voto) {
            emprendimientos.add(x.getVotoAEmprendimiento().getNombre());
        }
        for (int i = 0; i < emprendimientos.stream().count(); i++) {
            int itera = i;
            mapRanking.put(emprendimientos.get(i), emprendimientos.stream().filter(f -> f.equals(emprendimientos.get(itera))).count());
        }
        if (!mapRanking.isEmpty()) {
            mapRanking.values().stream();
            return ResponseEntity.ok(mapRanking);
        }
        return new ResponseEntity<>("No hay votos registrados en este evento o el evento es inexistente", HttpStatus.NOT_FOUND);
    }
}