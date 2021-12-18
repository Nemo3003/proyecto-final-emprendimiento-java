package com.actividadFinal.ModuloJava2021.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "idUrl")
    private Long id;
    @Getter @Setter
    private String name;
    public Url(String name){
        this.name = name;
    }
    public Url() {
    }
}