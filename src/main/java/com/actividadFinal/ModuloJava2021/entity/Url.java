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

// Con las sgtes lineas de codigo estaba intentando de implementar algo que nunca use:
//    public void agregarEmprendimiento(Emprendimiento EmpIngresado){
//        if(emprendimiento == null) emprendimiento = new ArrayList<>();
//        emprendimiento.add(EmpIngresado);
//        EmpIngresado.setUrls((Collection<Url>) this);
//    }
//    public void removerUrl(Emprendimiento EmpIngresado) {
//        emprendimiento.remove(EmpIngresado);
//        EmpIngresado.setUrls(null);
//    }

//    @Getter @Setter
//    @ManyToMany(mappedBy = "urls", cascade = CascadeType.ALL)
//    private Collection<Emprendimiento> emprendimiento = new HashSet();
