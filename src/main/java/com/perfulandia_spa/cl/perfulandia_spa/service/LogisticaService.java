package com.perfulandia_spa.cl.perfulandia_spa.service;

import com.perfulandia_spa.cl.perfulandia_spa.model.Logistica;
import com.perfulandia_spa.cl.perfulandia_spa.repository.LogisticaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LogisticaService {

    @Autowired
    private LogisticaRepository logisticaRepository;
    public List<Logistica> findAll() {
        return logisticaRepository.findAll();
    }
    public Logistica findById(Long id) {
        return logisticaRepository.findById(id).orElse(null);
    }
    public Logistica save(Logistica logistica) {
        return logisticaRepository.save(logistica);
    }
    public void deleteById(Long id) {
        logisticaRepository.deleteById(id);
    }
}
