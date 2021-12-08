package com.prueba.ronyreyna_inventarios.service;

import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda_Productos;
import com.prueba.ronyreyna_inventarios.repository.ClienteRepository;
import com.prueba.ronyreyna_inventarios.repository.TiendaProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Tie;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TiendaProductoService {
    private final TiendaProductoRepository tiendaProductoRepository;

    public Tienda_Productos findByTiendaAndProducto(Tienda tienda, Producto producto){
        return tiendaProductoRepository.findByTiendaAndProducto(tienda, producto);
    }

    public Tienda_Productos save(Tienda_Productos tiendaProductos){
        return tiendaProductoRepository.save(tiendaProductos);
    }

    public void asignarProductoTienda(Tienda tienda, Producto producto, Boolean estado) {
        Tienda_Productos byTiendaAndProducto = findByTiendaAndProducto(tienda, producto);
        if(Objects.nonNull(byTiendaAndProducto)){
            byTiendaAndProducto.setEstado(estado);
            save(byTiendaAndProducto);
        }else{
            Tienda_Productos tiendaProductos = new Tienda_Productos();
            tiendaProductos.setTienda(tienda);
            tiendaProductos.setProducto(producto);
            tiendaProductos.setEstado(estado);
            save(tiendaProductos);
        }
    }
}
