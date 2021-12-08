package com.prueba.ronyreyna_inventarios.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "TIENDA_PRODUCTOS")
public class Tienda_Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "ID_TIENDA")
    private Tienda tienda;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO")
    private Producto producto;
    @Column(name = "ESTADO")
    private Boolean estado;
}
