package com.actividadFinal.ModuloJava2021.dtos;

import com.actividadFinal.ModuloJava2021.entity.Emprendimiento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
//dtos es para pasar un json más resumido.
public class EmprendimientoDto implements Serializable{
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private String contenido;
    @Getter @Setter
    private BigInteger recaudacion;
    @Getter @Setter
    private Boolean publicado;
    @Getter @Setter
    private Collection tags;
    @Getter @Setter
    private Collection urls;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Getter @Setter
    private Date fechaCreacion;

    public EmprendimientoDto(Long id, String nombre, String descripcion, String contenido, BigInteger recaudacion, Boolean publicado, Collection tags, Collection urls, Date fechaCreacion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.recaudacion = recaudacion;
        this.publicado = publicado;
        this.fechaCreacion = fechaCreacion;
        this.tags = tags;
        this.urls = urls;

    }
    public EmprendimientoDto(){}
    public static EmprendimientoDto EmprendimientoAEmpDto(Emprendimiento s){
        EmprendimientoDto dto = new EmprendimientoDto();
        dto.setId(s.getIdEmprendimiento());
        dto.setNombre(s.getNombre());
        dto.setContenido(s.getContenido());
        dto.setDescripcion(s.getDescripcion());
        dto.setRecaudacion(s.getRecaudacion());
        dto.setPublicado(s.getPublicado());
        dto.setFechaCreacion(s.getFechaCreacion());
        dto.setTags(s.getTags());
        dto.setUrls(s.getUrls());
        return dto;
    }
}
