package com.prueba.ronyreyna_inventarios.repository;

import com.prueba.ronyreyna_inventarios.models.entity.DetallePedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends CrudRepository<DetallePedido, Integer> {

}
