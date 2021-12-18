package com.actividadFinal.ModuloJava2021.controller;

import com.actividadFinal.ModuloJava2021.dtos.VotoDto;
import com.actividadFinal.ModuloJava2021.entity.Emprendimiento;
import com.actividadFinal.ModuloJava2021.entity.Evento;
import com.actividadFinal.ModuloJava2021.entity.Usuario;
import com.actividadFinal.ModuloJava2021.entity.Voto;
import com.actividadFinal.ModuloJava2021.enums.VotoGenerado;
import com.actividadFinal.ModuloJava2021.repository.EmprendimientoRepository;
import com.actividadFinal.ModuloJava2021.repository.EventoRepository;
import com.actividadFinal.ModuloJava2021.repository.UsuarioRepository;
import com.actividadFinal.ModuloJava2021.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/voto")
public class VotoController {
    @Autowired
    private VotoService votoService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private EventoRepository eventoRepository;
    private VotoDto votoDto;
    @PostMapping("crearVotoIdUsuario/{idUsuario}/IdEvento/{idEvento}/IdEmprendimiento/{idEmprendimiento}/StringGeneradoPor/{idGeneradoPor}")
    public ResponseEntity<?> altaVoto(
                                      @PathVariable("idUsuario") Long idUsuario,
                                      @PathVariable("idEvento") Long idEvento,
                                      @PathVariable("idEmprendimiento") Long idEmprendimiento,
                                      @PathVariable("idGeneradoPor") int idGeneradoPor){
        Evento evento = eventoRepository.getById(idEvento);
        Usuario usuario = usuarioRepository.getById(idUsuario);
        Emprendimiento emprendimiento = emprendimientoRepository.getById(idEmprendimiento);
        List<VotoGenerado> votoGenerado = List.of(VotoGenerado.mobile, VotoGenerado.web, VotoGenerado.servicio);
        Voto votoNuevo = new Voto();
        votoNuevo.setUsername(usuario);
        votoNuevo.setEvento(evento);
        votoNuevo.setVotoAEmprendimiento(emprendimiento);
        votoNuevo.setGeneradoPor(votoGenerado.get(idGeneradoPor));
        try {
            votoService.crearVoto(votoNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(VotoDto.VotoAVotoDto(votoNuevo));
        }catch(Exception x){
            return new ResponseEntity<>("Datos incompletos o erróneos", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> todosLosVotos(){
        List<Voto> votoLista = votoService.allVotos();
        if(!votoLista.isEmpty()){
            List<VotoDto> listaDto = new ArrayList<>();
            for (Voto s: votoLista) listaDto.add(VotoDto.VotoAVotoDto(s));
        return ResponseEntity.ok(listaDto);
        }
        return new ResponseEntity<>("No hay ningun voto", HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "buscarPorUsername/{username}")
    public ResponseEntity<?> votosUsername(@PathVariable String username){
        List<Voto> listaUsernameVoto = votoService.buscarVotoPorUsuario(username);
        if(!listaUsernameVoto.isEmpty()){
            List<VotoDto> listaDto = new ArrayList<>();
            for (Voto s: listaUsernameVoto) listaDto.add(VotoDto.VotoAVotoDto(s));
            return new ResponseEntity<>(listaDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("El username no existe o no tiene votos", HttpStatus.NOT_FOUND);
    }
}