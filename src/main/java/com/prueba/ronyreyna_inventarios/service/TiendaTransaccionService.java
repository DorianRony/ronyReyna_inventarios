package com.prueba.ronyreyna_inventarios.service;

import com.prueba.ronyreyna_inventarios.models.entity.Cliente;
import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda_Transacciones;
import com.prueba.ronyreyna_inventarios.repository.ClienteRepository;
import com.prueba.ronyreyna_inventarios.repository.TiendaTransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TiendaTransaccionService {
    private final TiendaTransaccionRepository tiendaTransaccionRepository;

    public void registoMovimientoTienda(Cliente cliente, Tienda tienda, Producto producto, Integer cantidad){
        Tienda_Transacciones tienda_transacciones = new Tienda_Transacciones();
        tienda_transacciones.setTienda(tienda);
        tienda_transacciones.setProducto(producto);
        tienda_transacciones.setCliente(cliente);
        tienda_transacciones.setCantidad(cantidad);
        tiendaTransaccionRepository.save(tienda_transacciones);
    }
}
