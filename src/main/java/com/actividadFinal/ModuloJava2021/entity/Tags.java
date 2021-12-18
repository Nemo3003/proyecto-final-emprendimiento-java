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

// Con las sgtes lineas de codigo estaba intentando de implementar algo que nunca use:
//    @Getter @Setter
//    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
//    private Collection<Emprendimiento> tags = new HashSet<>();
