package com.prueba.ronyreyna_inventarios.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @NotNull
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @NotNull
    @Column(name = "NOMBRE")
    private String name;
    @Column(name = "FOTO")
    @Lob
    @JsonIgnore
    private byte[] foto;
}
