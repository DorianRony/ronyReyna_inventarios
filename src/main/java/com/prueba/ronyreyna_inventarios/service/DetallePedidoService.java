package com.prueba.ronyreyna_inventarios.service;

import com.prueba.ronyreyna_inventarios.models.entity.DetallePedido;
import com.prueba.ronyreyna_inventarios.repository.DetallePedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedido save(DetallePedido detallePedido){
        return detallePedidoRepository.save(detallePedido);
    }
}
