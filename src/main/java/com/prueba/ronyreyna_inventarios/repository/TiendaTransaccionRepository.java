package com.prueba.ronyreyna_inventarios.repository;

import com.prueba.ronyreyna_inventarios.models.entity.Tienda_Transacciones;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiendaTransaccionRepository extends CrudRepository<Tienda_Transacciones, Integer> {

}
