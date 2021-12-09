package com.prueba.ronyreyna_inventarios.models.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class TiendaNumeroTransacciones {
    private String codTienda;
    private Date fecha;
    private Integer numeroTransacciones;
}
