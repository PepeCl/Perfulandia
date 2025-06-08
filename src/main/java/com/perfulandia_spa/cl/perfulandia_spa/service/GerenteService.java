package com.perfulandia_spa.cl.perfulandia_spa.service;

import com.perfulandia_spa.cl.perfulandia_spa.model.Gerente;
import com.perfulandia_spa.cl.perfulandia_spa.repository.GerenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public List<Gerente> findAll() {
        return gerenteRepository.findAll();
    }

    public Gerente findById(Long id) {
        return gerenteRepository.findById(id).get();
    }

    public Gerente save(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }   

    public void deleteById(Long id) {
        gerenteRepository.deleteById(id);
    }

}
