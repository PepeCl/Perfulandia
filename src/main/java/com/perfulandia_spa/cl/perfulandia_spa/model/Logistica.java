package com.perfulandia_spa.cl.perfulandia_spa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_envio", nullable = false)
    private String codigoEnvio;

    @Column(name = "id_pedido", nullable = false)
    private int idPedido;

    @Column(name = "origen", nullable = false)
    private String origenEnvio;

    @Column(name = "destino", nullable = false)
    private String destinoEnvio;

    @Column(name = "transportista", nullable = false)
    private String transportista;

    @Column(name = "fecha_envio", nullable = false)
    private Date fechaEnvio;

    @Column(name = "fecha_llegada", nullable = false)
    private Date fechaLlegada;

    @Column(name = "estado_envio", nullable = false)
    private String estadoEnvio;

}
