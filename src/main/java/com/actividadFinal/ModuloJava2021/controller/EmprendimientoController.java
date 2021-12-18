package com.actividadFinal.ModuloJava2021.controller;

import com.actividadFinal.ModuloJava2021.dtos.EmprendimientoDto;
import com.actividadFinal.ModuloJava2021.entity.Emprendimiento;
import com.actividadFinal.ModuloJava2021.service.EmprendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/emprendimiento")
public class EmprendimientoController {
    @Autowired
    private EmprendimientoService emprendimientoService;
    @PostMapping("/")
    public ResponseEntity<?> altaEmprendimiento(@RequestBody @Valid Emprendimiento emprendimiento) {
        emprendimientoService.guardarEmprend(emprendimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(EmprendimientoDto.EmprendimientoAEmpDto(emprendimiento));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmprendimientoPorId(@PathVariable int id){
        if(!emprendimientoService.buscarPorIdEmprend((long)id).isEmpty()){
            emprendimientoService.borrarEmprend((long)id);
            return ResponseEntity.ok("El emprendimiento fue borrado con exito");
        }
        return new ResponseEntity<>("No se encontro el emprendimiento con el id: "+ id, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEmprendimiento(@RequestBody @Valid Emprendimiento emprendModif, @PathVariable(value = "id") int id){
        Optional<Emprendimiento> emp = emprendimientoService.buscarPorIdEmprend((long)id);
        if(!emp.isPresent()){
            return new ResponseEntity<>("No hay ningún usuario identificado con el id: "+ id, HttpStatus.NOT_FOUND);
        }
        emp.get().setNombre(emprendModif.getNombre());
        emp.get().setDescripcion(emprendModif.getDescripcion());
        emp.get().setContenido(emprendModif.getContenido());
        emp.get().setRecaudacion(emprendModif.getRecaudacion());
        emp.get().setPublicado(emprendModif.getPublicado());
        emp.get().setTags(emprendModif.getTags());
        emp.get().setUrls(emprendModif.getUrls());
        emprendimientoService.guardarEmprend(emp.get())  ;
        return ResponseEntity.ok(EmprendimientoDto.EmprendimientoAEmpDto(emp.get()));
    }

    @GetMapping("/")
    public ResponseEntity<?> todosEmpren(){
        List<Emprendimiento> emprendimientos = emprendimientoService.todosEmprendimientos();
        if(!emprendimientos.isEmpty()){
            List<EmprendimientoDto> listaDtosEmp = new ArrayList<>();
            for (Emprendimiento s: emprendimientos) listaDtosEmp.add(EmprendimientoDto.EmprendimientoAEmpDto(s));
            return ResponseEntity.ok().body(listaDtosEmp);
        }
        return new ResponseEntity<>("No se encuentra ningún emprendimiento", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/buscarPorTag/{tag}")
    public ResponseEntity<?> buscarPalabra(@PathVariable String tag){
        if(!emprendimientoService.buscarEmprendPorTag(tag).isEmpty()){
            List<Emprendimiento> emprendimientos = emprendimientoService.buscarEmprendPorTag(tag);
            List<EmprendimientoDto> listaDtosEmp = new ArrayList<>();
            for (Emprendimiento s: emprendimientos) listaDtosEmp.add(EmprendimientoDto.EmprendimientoAEmpDto(s));
            return ResponseEntity.ok(listaDtosEmp);
        }
        return new ResponseEntity<>("No hay emprendimiento que coincida con su busqueda: " + tag, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/buscarSinPublicar")
    public ResponseEntity<?> buscarEmprSinPublicar(){
        List<Emprendimiento> emprendimientos = emprendimientoService.buscarEmpSinPublicar();
        if(!emprendimientoService.buscarEmpSinPublicar().isEmpty()){
            List<EmprendimientoDto> listaDtosEmp = new ArrayList<>();
            for (Emprendimiento s: emprendimientos) listaDtosEmp.add(EmprendimientoDto.EmprendimientoAEmpDto(s));
            return ResponseEntity.ok(listaDtosEmp);
        }
        return new ResponseEntity<>("No existe emprendimiento sin publicar.", HttpStatus.NOT_FOUND);
    }
}