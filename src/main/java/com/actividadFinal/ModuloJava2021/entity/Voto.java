package com.actividadFinal.ModuloJava2021.entity;

import com.actividadFinal.ModuloJava2021.enums.VotoGenerado;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "voto")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVoto")
    @Getter @Setter
    private Long idVoto;
    @Setter
    @Column(name = "generadoPor")
    private VotoGenerado generadoPor;
    @NotNull(message="no puede estar en blanco.")
    @Enumerated(EnumType.STRING)
    public VotoGenerado getGeneradoPor() {
        return generadoPor;
    }
    @CreationTimestamp
    @Getter @Setter
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
    @Getter @Setter
    @ManyToOne(optional = false)
    @MapKeyColumn(name = "email")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario username;
    @Getter @Setter
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Emprendimiento votoAEmprendimiento;

    @Getter @Setter
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Evento evento;
    public Voto(VotoGenerado generadoPor){
        this.generadoPor = generadoPor;
    }
    public Voto() {
    }
    public static Voto votoCreacion(Voto s, Evento evento, Emprendimiento votoAEmprendimiento, Usuario username){
        Voto dtos = new Voto();
        dtos.setGeneradoPor(s.getGeneradoPor());
        dtos.setUsername(username);
        dtos.setEvento(evento);
        dtos.setVotoAEmprendimiento(votoAEmprendimiento);
        return dtos;}
}