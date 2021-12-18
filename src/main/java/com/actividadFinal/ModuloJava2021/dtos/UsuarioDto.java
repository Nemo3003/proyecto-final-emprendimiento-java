package com.actividadFinal.ModuloJava2021.dtos;

import com.actividadFinal.ModuloJava2021.entity.Usuario;
import com.actividadFinal.ModuloJava2021.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
public class UsuarioDto implements Serializable {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String apellido;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String ciudad;
    @Getter @Setter
    private String provincia;
    @Getter @Setter
    private String pais;
    @Getter @Setter
    private TipoUsuario tipo;
    @Getter @Setter
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaCreacion;

    public UsuarioDto( String nom, String ape, String email, String ciudad, String provincia, String pais, TipoUsuario tipo){
            super();
            this.nombre = nom;
            this.apellido = ape;
            this.email = email;
            this.ciudad = ciudad;
            this.provincia = provincia;
            this.pais = pais;
            this.tipo = tipo;
    }
    public UsuarioDto(){}
    public static UsuarioDto UsuarioAUsuarioDto(Usuario usuario){
            UsuarioDto dto = new UsuarioDto();
            dto.setId(usuario.getId());
            dto.setFechaCreacion(usuario.getFechaCreacion());
            dto.setNombre(usuario.getNombre());
            dto.setApellido(usuario.getApellido());
            dto.setEmail(usuario.getEmail());
            dto.setCiudad(usuario.getCiudad());
            dto.setProvincia(usuario.getProvincia());
            dto.setPais(usuario.getPais());
            dto.setTipo(usuario.getTipo());
            return dto;
      }
}