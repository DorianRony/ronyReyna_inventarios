package com.prueba.ronyreyna_inventarios.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "PRODUCTO")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODIGO")
    private String cod;
    @Column(name = "NOMBRE")
    private String name;
    @Column(name = "PRECIO")
    private BigDecimal price;
    @Column(name = "STOCK")
    private Integer stock;
}
