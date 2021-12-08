package com.prueba.ronyreyna_inventarios.repository;

import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiendaRepository extends CrudRepository<Tienda, Integer> {
    Tienda findByCod(String cod);
    Tienda findByName(String name);
}
