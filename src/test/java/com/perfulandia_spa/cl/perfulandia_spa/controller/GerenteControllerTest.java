package com.perfulandia_spa.cl.perfulandia_spa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia_spa.cl.perfulandia_spa.model.Gerente;
import com.perfulandia_spa.cl.perfulandia_spa.service.GerenteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GerenteController.class)
public class GerenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerenteService gerenteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarGerentes() throws Exception {
        Gerente g1 = new Gerente();
        g1.setId(1L);
        g1.setNombreProducto("Producto A");

        Gerente g2 = new Gerente();
        g2.setId(2L);
        g2.setNombreProducto("Producto B");

        Mockito.when(gerenteService.findAll()).thenReturn(Arrays.asList(g1, g2));

        mockMvc.perform(get("/api/gerente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreProducto").value("Producto A"))
                .andExpect(jsonPath("$[1].nombreProducto").value("Producto B"));
    }

    @Test
    void testBuscarGerentePorId() throws Exception {
        Gerente g = new Gerente();
        g.setId(1L);
        g.setNombreProducto("Producto X");

        Mockito.when(gerenteService.findById(1L)).thenReturn(g);

        mockMvc.perform(get("/api/gerente/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Producto X"));
    }

    @Test
    void testGuardarGerente() throws Exception {
        Gerente nuevo = new Gerente();
        nuevo.setId(3L);
        nuevo.setNombreProducto("Producto Nuevo");

        Mockito.when(gerenteService.save(any(Gerente.class))).thenReturn(nuevo);

        mockMvc.perform(post("/api/gerente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombreProducto").value("Producto Nuevo"));
    }

    @Test
    void testActualizarGerente() throws Exception {
        Gerente existente = new Gerente();
        existente.setId(1L);
        existente.setNombreProducto("Viejo");

        Gerente actualizado = new Gerente();
        actualizado.setId(1L);
        actualizado.setNombreProducto("Actualizado");

        Mockito.when(gerenteService.findById(1L)).thenReturn(existente);
        Mockito.when(gerenteService.save(any(Gerente.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/gerente/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Actualizado"));
    }
}
