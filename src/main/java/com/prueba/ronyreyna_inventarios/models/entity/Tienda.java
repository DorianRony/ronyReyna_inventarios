package com.prueba.ronyreyna_inventarios.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "TIENDA")
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODIGO")
    private String cod;
    @Column(name = "NOMBRE")
    private String name;
}
