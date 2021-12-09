package com.prueba.ronyreyna_inventarios.controller;

import com.prueba.ronyreyna_inventarios.models.DTO.PedidoCliente;
import com.prueba.ronyreyna_inventarios.models.entity.Pedido;
import com.prueba.ronyreyna_inventarios.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inventario/pedidos")
public class PedidosController {
    private final PedidoService pedidoService;

    @PostMapping(path = "/guardar",produces = "application/json")
    public Pedido guardarPedido(Pedido pedido){
        return pedidoService.save(pedido);
    }

    @PostMapping(path = "/confirmarPedido",produces = "application/json")
    public PedidoCliente confirmarPedido(PedidoCliente pedidoCliente){
        return pedidoService.realizarPedido(pedidoCliente);
    }


}
