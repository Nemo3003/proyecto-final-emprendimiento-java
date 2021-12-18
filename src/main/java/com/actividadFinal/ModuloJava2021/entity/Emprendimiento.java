package com.actividadFinal.ModuloJava2021.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "emprendimiento")
public class Emprendimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(unique = true, nullable = false, name = "id")
    private long idEmprendimiento;
    @Size(max = 20, message="del emprendimiento debe tener entre 4 y 20 caracteres")
    @NotBlank(message = "no debe estar en blanco.")
    @Getter @Setter @Column(name = "nombre", nullable = false)
    private String nombre;
    @Size(min = 10, max = 250, message="del emprendimiento debe tener entre 10 y 250 caracteres")
    @Getter @Setter @Column(name = "descripcion", nullable = false)
    @NotBlank(message = "no debe estar en blanco.")
    private String descripcion;
    @Size(min = 10, max = 500, message="del emprendimiento debe tener entre 10 y 500 caracteres")
    @NotBlank(message = "no debe estar en blanco")
    @Getter @Setter @Column(name = "contenido", length = 500, nullable = false)
    private String contenido;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Getter @Setter @Column(name = "fechaCreacion")
    private Date fechaCreacion;
    @Getter @Setter @Column(name = "recaudacion")
    private BigInteger recaudacion;
    @NotNull(message = "no debe estar en blanco")
    @Getter @Setter @Column(name = "publicado", nullable = false)
    private Boolean publicado;
    @Getter @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "url_nombre")
    private Collection<Url> urls = new HashSet();
    @Getter  @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tags")
    private Collection<Tags> tags = new HashSet();
    @Getter @Setter
    @JoinColumn(name = "id")
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Evento> suscriptores = new HashSet<>();
    public Emprendimiento (String nombre, String descripcion, String contenido, BigInteger recaudacion, Boolean publicado, Collection<Url> url, List<Voto> votoEmprendimiento
    , Collection<Tags> tags, Collection<Evento> suscriptores){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.publicado = publicado;
        this.urls = url;
        this.recaudacion = recaudacion;
        this.tags = tags;
        this.suscriptores = suscriptores;
    }
    public Emprendimiento() {
    }
}
