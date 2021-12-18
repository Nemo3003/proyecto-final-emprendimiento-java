package com.actividadFinal.ModuloJava2021.service;

import com.actividadFinal.ModuloJava2021.entity.Emprendimiento;
import com.actividadFinal.ModuloJava2021.entity.Tags;
import com.actividadFinal.ModuloJava2021.repository.EmprendimientoRepository;
import com.actividadFinal.ModuloJava2021.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
//todos los metodos :)
@Service
public class EmprendimientoService {
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private TagRepository tagRepository;
    @Transactional
    public Emprendimiento guardarEmprend(Emprendimiento emprendimiento){return emprendimientoRepository.save(emprendimiento);}
    @Transactional
    public void borrarEmprend(Long id){emprendimientoRepository.deleteById(id);}
    @Transactional
    public List<Emprendimiento> todosEmprendimientos(){return emprendimientoRepository.findAll();}
//    public Iterable<Emprendimiento> todosEmprendimientos(){return emprendimientoRepository.findAll();}
    @Transactional
    public Optional<Emprendimiento> buscarPorIdEmprend(Long id){return emprendimientoRepository.findById(id);}
    @Transactional
    public List<Emprendimiento> buscarEmprendPorTag(String tags){
        List<Emprendimiento> list = new ArrayList<>();
        for (Emprendimiento x : todosEmprendimientos()) {
            for (Tags j : x.getTags()) {
                if(j.getNombre().toLowerCase(Locale.ROOT).contains(tags.toLowerCase(Locale.ROOT))){
                    list.add(x);
                }
            }
        }
        return list;
    }
    @Transactional
    public List<Emprendimiento> buscarEmpSinPublicar(){
        return emprendimientoRepository.findAll().stream().filter(p -> p.getPublicado().equals(false)).collect(Collectors.toList());
    }
}
