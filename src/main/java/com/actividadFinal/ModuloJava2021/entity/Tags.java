package com.actividadFinal.ModuloJava2021.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long idTag;
    @Getter @Setter
    private String nombre;
    public Tags(String nombre){
        this.nombre = nombre;
    }
    public Tags() {
    }
}
