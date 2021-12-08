package com.prueba.ronyreyna_inventarios.models.DTO;

import com.prueba.ronyreyna_inventarios.models.entity.Cliente;
import com.prueba.ronyreyna_inventarios.models.entity.Pedido;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PedidoCliente {
    private String identificacionCLiente;
    private Cliente cliente;
    private List<DetallePedidoCliente> detallePedidos;
    private Integer idPedido;
    private String mensaje;
}
