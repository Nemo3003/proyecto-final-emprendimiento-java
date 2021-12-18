package com.actividadFinal.ModuloJava2021.dtos;

import com.actividadFinal.ModuloJava2021.enums.VotoGenerado;
import com.actividadFinal.ModuloJava2021.entity.Voto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class VotoDto implements Serializable {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String evento;
    @Getter @Setter
    private String votoAEmprendimiento;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Getter @Setter
    private Date fechaDeCreacion;
    @Getter @Setter
    private VotoGenerado creadoPor;

    public VotoDto(Long id, String username, String evento, String emprendimiento, Date fechaCreacion, VotoGenerado creadoPor){
        this.id = id;
        this.username = username;
        this.evento = evento;
        this.votoAEmprendimiento = emprendimiento;
        this.fechaDeCreacion = fechaCreacion;
        this.creadoPor=creadoPor;
    }
    public VotoDto(){}
    public static VotoDto VotoAVotoDto(Voto s){
        VotoDto dto = new VotoDto();
        dto.setId(s.getIdVoto());
        dto.setUsername(s.getUsername().getEmail());
        dto.setFechaDeCreacion(s.getFechaCreacion());
        dto.setVotoAEmprendimiento(s.getVotoAEmprendimiento().getNombre());
        dto.setEvento(s.getEvento().getDetallesEvento());
        dto.setCreadoPor(s.getGeneradoPor());
        return dto;
    }
}