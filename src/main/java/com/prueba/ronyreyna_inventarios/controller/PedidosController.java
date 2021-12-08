package com.prueba.ronyreyna_inventarios.controller;

import com.prueba.ronyreyna_inventarios.models.DTO.PedidoCliente;
import com.prueba.ronyreyna_inventarios.models.entity.*;
import com.prueba.ronyreyna_inventarios.repository.DetallePedidoRepository;
import com.prueba.ronyreyna_inventarios.repository.PedidoRepository;
import com.prueba.ronyreyna_inventarios.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
