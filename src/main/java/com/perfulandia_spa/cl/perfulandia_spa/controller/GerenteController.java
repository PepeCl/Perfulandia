package com.perfulandia_spa.cl.perfulandia_spa.controller;

import com.perfulandia_spa.cl.perfulandia_spa.model.Gerente;
import com.perfulandia_spa.cl.perfulandia_spa.service.GerenteService;
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
@RequestMapping("/api/gerente")
public class GerenteController {


    @Autowired
    private GerenteService gerenteService;

    @GetMapping
    public ResponseEntity<List<Gerente>> listar(){
        List<Gerente> gerente = gerenteService.findAll();
        if (gerente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(gerente, HttpStatus.OK);
        }
    }


    @PostMapping
    public ResponseEntity<Gerente> guardar(@RequestBody Gerente gerente){
        Gerente gerenteNuevo = gerenteService.save(gerente);
        return  ResponseEntity.status(HttpStatus.CREATED).body(gerenteNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> buscar(@PathVariable Integer id){
        try{
            Gerente gerente = gerenteService.findById(id.longValue());
            return ResponseEntity.ok(gerente);
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

        @PutMapping("/{id}")
    public ResponseEntity<Gerente> actualizar(@PathVariable Integer id, @RequestBody Gerente gerente){
        try {
            Gerente geren = gerenteService.findById(id.longValue());
            geren.setId(id.longValue());
            geren.setNombreProducto(gerente.getNombreProducto());
            geren.setCategoria(gerente.getCategoria());
            geren.setStockMinimo(gerente.getStockMinimo());
            geren.setStockActual(gerente.getStockActual());
            geren.setProveedor(gerente.getProveedor());
            geren.setPrecioUnitario(gerente.getPrecioUnitario());
            geren.setPrecioVenta(gerente.getPrecioVenta());
            gerenteService.save(geren);
            return ResponseEntity.ok(geren);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}

