package com.prueba.ronyreyna_inventarios.controller;

import com.prueba.ronyreyna_inventarios.models.entity.DetallePedido;
import com.prueba.ronyreyna_inventarios.service.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inventario/detallepedidos")
public class DetallePedidosController {

    private final DetallePedidoService detallePedidoService;

    @PostMapping(path = "/guardar/", produces = "application/json")
    public DetallePedido guardarDetallePedido(DetallePedido detallePedido)
    {
        return detallePedidoService.save(detallePedido);
    }


}
