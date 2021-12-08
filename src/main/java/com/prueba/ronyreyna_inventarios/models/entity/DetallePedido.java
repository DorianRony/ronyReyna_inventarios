package com.prueba.ronyreyna_inventarios.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "DETALLE_PEDIDO")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO")
    private Tienda_Productos tiendaProductos;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
}
