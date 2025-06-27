package com.perfulandia_spa.cl.perfulandia_spa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia_spa.cl.perfulandia_spa.model.Usuario;
import com.perfulandia_spa.cl.perfulandia_spa.service.UsuarioService;
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

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarUsuarios() throws Exception {
        Usuario u1 = new Usuario();
        u1.setId(1L);
        u1.setPrimerNombre("Juan");

        Usuario u2 = new Usuario();
        u2.setId(2L);
        u2.setPrimerNombre("Ana");

        Mockito.when(usuarioService.findAll()).thenReturn(Arrays.asList(u1, u2));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].primerNombre").value("Juan"))
                .andExpect(jsonPath("$[1].primerNombre").value("Ana"));
    }

    @Test
    void testBuscarUsuarioPorId() throws Exception {
        Usuario u = new Usuario();
        u.setId(1L);
        u.setPrimerNombre("Pedro");

        Mockito.when(usuarioService.findById(1L)).thenReturn(u);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primerNombre").value("Pedro"));
    }

    @Test
    void testCrearUsuario() throws Exception {
        Usuario nuevo = new Usuario();
        nuevo.setId(3L);
        nuevo.setPrimerNombre("Laura");

        Mockito.when(usuarioService.save(any(Usuario.class))).thenReturn(nuevo);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.primerNombre").value("Laura"));
    }

    @Test
    void testActualizarUsuario() throws Exception {
        Usuario original = new Usuario();
        original.setId(1L);
        original.setPrimerNombre("Carlos");

        Usuario actualizado = new Usuario();
        actualizado.setId(1L);
        actualizado.setPrimerNombre("Carlos Actualizado");

        Mockito.when(usuarioService.findById(1L)).thenReturn(original);
        Mockito.when(usuarioService.save(any(Usuario.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primerNombre").value("Carlos Actualizado"));
    }
}