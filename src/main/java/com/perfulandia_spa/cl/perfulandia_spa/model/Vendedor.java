package com.perfulandia_spa.cl.perfulandia_spa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "vendedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_venta", nullable = true)
    private Date fechaVenta;

    @Column(name = "rut_cliente", nullable = false)
    private String rutCliente;
    
    @Column(name = "detalle_Producto", nullable = false)
    private String detalleProducto;

    @Column(name = "total_Productos", nullable = false)
    private float totalProducto;

    @Column(name = "total_Bruto", nullable = false)
    private float totalBruto;

    @Column(name = "descuento", nullable = false)
    private float descuento;

    @Column(name = "total_neto", nullable = false)
    private float totalNeto;
}