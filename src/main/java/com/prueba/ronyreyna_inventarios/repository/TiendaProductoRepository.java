package com.prueba.ronyreyna_inventarios.repository;

import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda_Productos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiendaProductoRepository extends CrudRepository<Tienda_Productos, Integer> {
    Tienda_Productos findByTiendaAndProducto(Tienda tienda, Producto producto);
}
