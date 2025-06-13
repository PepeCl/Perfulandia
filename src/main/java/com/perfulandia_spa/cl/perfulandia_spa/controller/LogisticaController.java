package com.perfulandia_spa.cl.perfulandia_spa.controller;

import com.perfulandia_spa.cl.perfulandia_spa.model.Logistica;
import com.perfulandia_spa.cl.perfulandia_spa.service.LogisticaService;
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
@RequestMapping("/api/logistica")
public class LogisticaController{

    @Autowired 
    private LogisticaService logisticaService; 

    @GetMapping 
    public ResponseEntity<List<Logistica>> listar() {
        List<Logistica> envios = logisticaService.findAll();
        if (envios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(envios, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Logistica> guardar(@RequestBody Logistica logistica) {
        Logistica nuevoEnvio = logisticaService.save(logistica);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logistica> buscar(@PathVariable Long id) {
        try {
            Logistica envio = logisticaService.findById(id);
            return ResponseEntity.ok(envio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Logistica> actualizar(@PathVariable Long id, @RequestBody Logistica logistica) {
        try {
            Logistica envioExistente = logisticaService.findById(id);
            envioExistente.setCodigoEnvio(logistica.getCodigoEnvio());
            envioExistente.setIdPedido(logistica.getIdPedido());
            envioExistente.setOrigenEnvio(logistica.getOrigenEnvio());
            envioExistente.setDestinoEnvio(logistica.getDestinoEnvio());
            envioExistente.setTransportista(logistica.getTransportista());
            envioExistente.setFechaEnvio(logistica.getFechaEnvio());
            envioExistente.setFechaLlegada(logistica.getFechaLlegada());
            envioExistente.setEstadoEnvio(logistica.getEstadoEnvio());

            Logistica actualizado = logisticaService.save(envioExistente);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

} 