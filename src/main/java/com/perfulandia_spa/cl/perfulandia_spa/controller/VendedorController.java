package com.perfulandia_spa.cl.perfulandia_spa.controller;

import com.perfulandia_spa.cl.perfulandia_spa.model.Vendedor;
import com.perfulandia_spa.cl.perfulandia_spa.service.VendedorService;
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
@RequestMapping("/api/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<List<Vendedor>> listarRegistros() {
        List<Vendedor> registros = vendedorService.findAll();
        if (registros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(registros, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Vendedor> registrar(@RequestBody Vendedor vendedor) {
        Vendedor nuevoRegistro = vendedorService.save(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRegistro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> buscarPorId(@PathVariable Long id) {
        Vendedor registro = vendedorService.findById(id);
        if (registro != null) {
            return ResponseEntity.ok(registro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> actualizar(@PathVariable Long id, @RequestBody Vendedor vendedor) {
        Vendedor existente = vendedorService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setFechaVenta(vendedor.getFechaVenta());
        existente.setRutCliente(vendedor.getRutCliente());
        existente.setDetalleProducto(vendedor.getDetalleProducto());
        existente.setTotalBruto(vendedor.getTotalBruto());
        existente.setDescuento(vendedor.getDescuento());
        existente.setTotalNeto(vendedor.getTotalNeto());

        vendedorService.save(existente);
        return ResponseEntity.ok(existente);
    }
}