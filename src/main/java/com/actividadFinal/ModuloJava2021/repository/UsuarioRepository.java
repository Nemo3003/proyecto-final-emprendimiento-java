package com.actividadFinal.ModuloJava2021.repository;

import com.actividadFinal.ModuloJava2021.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
