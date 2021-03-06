package com.prueba.ronyreyna_inventarios.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@Table(name = "CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "IDENTIFICACION", nullable = false, unique = true)
    private String identificacion;
    @Column(name = "NOMBRE",nullable = false)
    private String name;
    @Column(name = "FOTO")
    @Lob
    @JsonIgnore
    private byte[] foto;
}
