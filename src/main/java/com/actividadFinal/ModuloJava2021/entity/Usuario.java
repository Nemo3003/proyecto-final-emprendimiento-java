package com.actividadFinal.ModuloJava2021.entity;

import com.actividadFinal.ModuloJava2021.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(unique = true, nullable = false, name = "id")
    private Long id;

    @Getter @Setter @Column(name = "nombre", nullable = false, length = 50)
     @NotBlank(message = " no debe estar en blanco.")
    private String nombre;

    @Getter @Setter @Column(name = "apellido", nullable = false, length = 50)
    @NotBlank(message = " no debe estar en blanco.")
    private String apellido;

    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message="no cumple con los requisitos")
    @Getter @Setter @Column(unique = true, name = "email")
    @NotBlank(message = "no debe estar en blanco.")
    private String email;

//    servia para que en sea ignorado en el json - finalmente cree un dto para que nosea traido el password
//    @JsonIgnore
//    @Getter(onMethod = @__( @JsonIgnore))
    @Getter @Setter @Column(name = "password", nullable = false)
    @NotBlank(message = "no debe estar en blanco.")
    private  String password;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Getter @Setter @Column(name = "fechaCreacion", nullable = false)
    private Date fechaCreacion;

    @Getter @Setter @Column(name = "ciudad", nullable = false)
    @NotBlank(message = "no debe estar en blanco.")
    private String ciudad;

    @Getter @Setter @Column(name = "provincia", nullable = false)
    @NotBlank(message = "no debe estar en blanco.")
    private String provincia;

    @Getter @Setter @Column(name = "pais", nullable = false)
    @NotBlank(message = "no debe estar en blanco.")
    private String pais;

    @Enumerated(EnumType.STRING)
    @Setter @Column(name = "tipo")
    private TipoUsuario tipo;

    @NotNull(message="no puede estar en blanco.")
    public TipoUsuario getTipo() {
        return tipo;
    }

    public Usuario ( String nom, String ape, String email, String ciudad, String provincia, String pais, TipoUsuario tipo, String password){
        super();
        this.nombre = nom;
        this.apellido = ape;
        this.email = email;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
        this.tipo = tipo;
        this.password = password;
    }
    public Usuario() {
    }
}

// Con las sgtes lineas de codigo estaba intentando de implementar algo que nunca use:
//    @Getter(onMethod = @__( @JsonIgnore)) @Setter
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "username", nullable = false)
//    private List<Voto> votosUsuario;
