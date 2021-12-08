package com.prueba.ronyreyna_inventarios.models.DTO;

import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda_Productos;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DetallePedidoCliente {
    private String codTienda;
    private String codProducto;
    private Tienda_Productos tiendaProductos;
    private Integer cantidad;
}
