package com.actividadFinal.ModuloJava2021.service;

import com.actividadFinal.ModuloJava2021.entity.Usuario;
import com.actividadFinal.ModuloJava2021.repository.UsuarioRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Transactional(readOnly = true)
//    public Iterable<Usuario> obtUsuarios(){return usuarioRepository.findAll();}
    public List<Usuario> obtUsuarios(){return usuarioRepository.findAll();}
    @Transactional(readOnly = true)
    public Optional<Usuario> obtUnUsuarioPorId(Long id) {return  usuarioRepository.findById(id);}
    @Transactional(readOnly = true)
    public List<Usuario> obtUsuariosPorCiudad(String ciudad){return usuarioRepository.findAll().stream().filter(p -> {return p.getCiudad().toLowerCase(Locale.ROOT).contains(ciudad.toLowerCase(Locale.ROOT));
    }).collect(Collectors.toList());}
    @Transactional(readOnly = true)
    public List<Usuario> obtUsuariosPorFechaPost(Date fecha){return usuarioRepository.findAll().stream().filter(p -> {return p.getFechaCreacion().after(fecha);
    }).collect(Collectors.toList());}
    @Transactional
    public Usuario guardarUsuario(Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        return usuarioRepository.save(usuario);}
    @Transactional
    public void borrarUsuario(Long id){usuarioRepository.deleteById(id);}

}
