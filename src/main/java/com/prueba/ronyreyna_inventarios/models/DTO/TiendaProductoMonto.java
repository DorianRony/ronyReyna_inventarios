package com.prueba.ronyreyna_inventarios.models.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TiendaProductoMonto {
    private String codTienda;
    private String codProducto;
    private BigDecimal monto;
}
