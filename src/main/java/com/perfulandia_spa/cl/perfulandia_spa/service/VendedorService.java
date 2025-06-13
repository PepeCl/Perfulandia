package com.perfulandia_spa.cl.perfulandia_spa.service;

import com.perfulandia_spa.cl.perfulandia_spa.model.Vendedor;
import com.perfulandia_spa.cl.perfulandia_spa.repository.VendedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    public Vendedor save(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Vendedor findById(Long id) {
        return vendedorRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        vendedorRepository.deleteById(id);
    }
}
