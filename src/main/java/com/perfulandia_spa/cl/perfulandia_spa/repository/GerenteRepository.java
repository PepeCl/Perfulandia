package com.perfulandia_spa.cl.perfulandia_spa.repository;

import com.perfulandia_spa.cl.perfulandia_spa.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {

    List<Gerente> findByIdGerente(String id_gerente);

    List<Gerente> findByRut_Gerente(String rut);

    List<Gerente> findByPrimerNombre(String primerNombre);

    List<Gerente> findBySegundoNombre(String segundoNombre);

    List<Gerente> findByApellidoPaterno(String apellidoPaterno);

    List<Gerente> findByApellidoMaterno(String apellidoMaterno);

    List<Gerente> findByEmail(String email);    
}

