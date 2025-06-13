package com.perfulandia_spa.cl.perfulandia_spa.repository;

import com.perfulandia_spa.cl.perfulandia_spa.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    List<Vendedor> findAll();

    List<Vendedor> findByRutCliente(Number rutCliente);

    List<Vendedor> findByFechaVenta(Date fechaVenta);

    List<Vendedor> findByTotalNeto(Number totalNeto);

    List<Vendedor> findByTotalBrutoGreaterThan(Number totalBruto);

    List<Vendedor> findByDescuentoGreaterThan(Number descuento);

    List<Vendedor> findByFechaVentaBetween(Date desde, Date hasta);

    List<Vendedor> findByTotalProducto(Number totalProducto);

    List<Vendedor> findByDetalleProducto(Number detalleProducto);
}