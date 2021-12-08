package com.prueba.ronyreyna_inventarios.repository;

import com.prueba.ronyreyna_inventarios.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    Cliente findByIdentificacion(String identificacion);
}
