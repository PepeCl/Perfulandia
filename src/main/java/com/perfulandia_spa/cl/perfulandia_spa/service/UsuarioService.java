package com.perfulandia_spa.cl.perfulandia_spa.service;

import com.perfulandia_spa.cl.perfulandia_spa.model.Usuario;
import com.perfulandia_spa.cl.perfulandia_spa.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }   

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

}
