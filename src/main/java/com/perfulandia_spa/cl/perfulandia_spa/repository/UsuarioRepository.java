package com.perfulandia_spa.cl.perfulandia_spa.repository;

import com.perfulandia_spa.cl.perfulandia_spa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByRut(String rut);

    List<Usuario> findByPrimerNombre(String primerNombre);

    List<Usuario> findBySegundoNombre(String segundoNombre);

    List<Usuario> findByApellidoPaterno(String apellidoPaterno);

    List<Usuario> findByApellidoMaterno(String apellidoMaterno);

    List<Usuario> findByEmail(String email);

    List<Usuario> findByRol(String rol);

    List<Usuario> findByInicioContrato(Date inicioContrato);

    
}
