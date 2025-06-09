package com.perfulandia_spa.cl.perfulandia_spa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gerente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "stock_minimo", nullable = false)
    private int stockMinimo;

    @Column(name = "stock_actual", nullable = false)
    private int stockActual;

    @Column(name = "proveedor", nullable = false)
    private String proveedor;

    @Column(name = "precio_unitario", nullable = false)
    private int precioUnitario;

    @Column(name = "precio_venta", nullable = false)
    private int precioVenta;

}
