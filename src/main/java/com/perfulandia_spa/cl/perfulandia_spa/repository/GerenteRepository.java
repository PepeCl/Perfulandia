package com.perfulandia_spa.cl.perfulandia_spa.repository;

import com.perfulandia_spa.cl.perfulandia_spa.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    List<Gerente> findAll();

    List<Gerente> findByNombreProducto(String nombreProducto);

    List<Gerente> findByCategoria(String categoria);

    List<Gerente> findByStockMinimo(int stockMinimo);

    List<Gerente> findByStockActual(int stockActual);

    List<Gerente> findByProveedor(String proveedor);

    List<Gerente> findByPrecioUnitario(int precioUnitario);

    List<Gerente> findByPrecioVenta(int precioVenta);

}

