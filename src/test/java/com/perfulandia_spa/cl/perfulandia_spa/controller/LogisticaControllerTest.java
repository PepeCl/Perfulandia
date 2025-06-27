package com.perfulandia_spa.cl.perfulandia_spa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia_spa.cl.perfulandia_spa.model.Logistica;
import com.perfulandia_spa.cl.perfulandia_spa.service.LogisticaService;
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

@WebMvcTest(LogisticaController.class)
public class LogisticaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogisticaService logisticaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarLogistica() throws Exception {
        Logistica l1 = new Logistica();
        l1.setId(1L);
        l1.setCodigoEnvio("ENV-001");

        Logistica l2 = new Logistica();
        l2.setId(2L);
        l2.setCodigoEnvio("ENV-002");

        Mockito.when(logisticaService.findAll()).thenReturn(Arrays.asList(l1, l2));

        mockMvc.perform(get("/api/logistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigoEnvio").value("ENV-001"))
                .andExpect(jsonPath("$[1].codigoEnvio").value("ENV-002"));
    }

    @Test
    void testBuscarLogisticaPorId() throws Exception {
        Logistica l = new Logistica();
        l.setId(1L);
        l.setCodigoEnvio("ENV-123");

        Mockito.when(logisticaService.findById(1L)).thenReturn(l);

        mockMvc.perform(get("/api/logistica/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigoEnvio").value("ENV-123"));
    }

    @Test
    void testGuardarLogistica() throws Exception {
        Logistica nueva = new Logistica();
        nueva.setId(3L);
        nueva.setCodigoEnvio("ENV-003");

        Mockito.when(logisticaService.save(any(Logistica.class))).thenReturn(nueva);

        mockMvc.perform(post("/api/logistica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codigoEnvio").value("ENV-003"));
    }

    @Test
    void testActualizarLogistica() throws Exception {
        Logistica existente = new Logistica();
        existente.setId(1L);
        existente.setCodigoEnvio("ENV-OLD");

        Logistica actualizado = new Logistica();
        actualizado.setId(1L);
        actualizado.setCodigoEnvio("ENV-NEW");

        Mockito.when(logisticaService.findById(1L)).thenReturn(existente);
        Mockito.when(logisticaService.save(any(Logistica.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/logistica/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigoEnvio").value("ENV-NEW"));
    }
}
