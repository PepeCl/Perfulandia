package com.perfulandia_spa.cl.perfulandia_spa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia_spa.cl.perfulandia_spa.model.Vendedor;
import com.perfulandia_spa.cl.perfulandia_spa.service.VendedorService;
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

@WebMvcTest(VendedorController.class)
public class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendedorService vendedorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarVendedores() throws Exception {
        Vendedor v1 = new Vendedor();
        v1.setId(1L);
        v1.setRutCliente("12345678-9");

        Vendedor v2 = new Vendedor();
        v2.setId(2L);
        v2.setRutCliente("98765432-1");

        Mockito.when(vendedorService.findAll()).thenReturn(Arrays.asList(v1, v2));

        mockMvc.perform(get("/api/vendedor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rutCliente").value("12345678-9"))
                .andExpect(jsonPath("$[1].rutCliente").value("98765432-1"));
    }

    @Test
    void testBuscarVendedorPorId() throws Exception {
        Vendedor v = new Vendedor();
        v.setId(1L);
        v.setRutCliente("12345678-9");

        Mockito.when(vendedorService.findById(1L)).thenReturn(v);

        mockMvc.perform(get("/api/vendedor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rutCliente").value("12345678-9"));
    }

    @Test
    void testRegistrarVendedor() throws Exception {
        Vendedor nuevo = new Vendedor();
        nuevo.setId(3L);
        nuevo.setRutCliente("11222333-4");

        Mockito.when(vendedorService.save(any(Vendedor.class))).thenReturn(nuevo);

        mockMvc.perform(post("/api/vendedor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.rutCliente").value("11222333-4"));
    }

    @Test
    void testActualizarVendedor() throws Exception {
        Vendedor existente = new Vendedor();
        existente.setId(1L);
        existente.setRutCliente("11111111-1");

        Vendedor actualizado = new Vendedor();
        actualizado.setId(1L);
        actualizado.setRutCliente("99999999-9");

        Mockito.when(vendedorService.findById(1L)).thenReturn(existente);
        Mockito.when(vendedorService.save(any(Vendedor.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/vendedor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rutCliente").value("99999999-9"));
    }
}
