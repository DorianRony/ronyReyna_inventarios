package com.prueba.ronyreyna_inventarios.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "TIENDA_TRANSACCIONES")
public class Tienda_Transacciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "ID_TIENDA")
    private Tienda tienda;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO")
    private Producto producto;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @PrePersist
    private void fecha(){
        this.fecha = new Date();
    }
}
