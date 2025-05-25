package com.perfulandia_spa.cl.perfulandia_spa.controller;

import com.perfulandia_spa.cl.perfulandia_spa.model.Usuario;
import com.perfulandia_spa.cl.perfulandia_spa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
    }


    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario){
        Usuario usuarioNuevo = usuarioService.save(usuario);
        return  ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Integer id){
        try{
            Usuario usuario = usuarioService.findById(id.longValue());
            return ResponseEntity.ok(usuario);
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

        @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario){
        try {
            Usuario usu = usuarioService.findById(id.longValue());
            usu.setId(id.longValue());
            usu.setRut(usuario.getRut());
            usu.setPrimerNombre(usuario.getPrimerNombre());
            usu.setSegundoNombre(usuario.getSegundoNombre());
            usu.setApellidoPaterno(usuario.getApellidoPaterno());
            usu.setApellidoMaterno(usuario.getApellidoMaterno());
            usu.setEmail(usuario.getEmail());
            usu.setRol(usuario.getRol());

            usuarioService.save(usu);
            return ResponseEntity.ok(usu);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
